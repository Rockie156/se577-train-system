package edu.drexel.TrainDemo.trips.services;

import edu.drexel.TrainDemo.trips.models.Itinerary;
import edu.drexel.TrainDemo.trips.models.Segment;
import edu.drexel.TrainDemo.trips.models.TripSearchRequest;
import edu.drexel.TrainDemo.trips.models.entities.StationEntity;
import edu.drexel.TrainDemo.trips.models.entities.StopTimeEntity;
import edu.drexel.TrainDemo.trips.models.entities.TripEntity;
import edu.drexel.TrainDemo.trips.repositories.StationRepository;
import edu.drexel.TrainDemo.trips.repositories.TripRepository;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TripService {
    private StationRepository stationRepository;
    private TripRepository tripRepository;

    public TripService(StationRepository stationRepository, TripRepository tripRepository) {
        this.stationRepository = stationRepository;
        this.tripRepository = tripRepository;
    }

    public List<Itinerary> getMatchingTrips(TripSearchRequest searchRequest) {
        String toId = searchRequest.getTo();
        String fromId = searchRequest.getFrom();

        StationEntity toStation = safeGetStationFromId(toId);
        StationEntity fromStation = safeGetStationFromId(fromId);

        List<GraphPath<StationEntity, StopTimeEntityEdge>> pathList = searchGraph(fromStation, toStation);

        List<Itinerary> itineraryList = new ArrayList<>();
        for (GraphPath<StationEntity, StopTimeEntityEdge> path : pathList) {
            List<StopTimeEntityEdge> edgeList = path.getEdgeList();

            List<Segment> segments = new ArrayList<>();
            for (StopTimeEntityEdge edge : edgeList) {
                StopTimeEntity fromStop = edge.getFrom();
                StopTimeEntity toStop = edge.getTo();
                segments.add(new Segment(fromStop, toStop));
            }

            itineraryList.add(new Itinerary(segments));
        }
//        return itineraryList;
        List<Itinerary> validItineraries = itineraryList.stream().filter(itinerary -> itinerary.isValid()).collect(Collectors.toList());
        return validItineraries;
    }

    public List<GraphPath<StationEntity, StopTimeEntityEdge>> searchGraph(StationEntity fromStation, StationEntity toStation) {
        Graph<StationEntity, StopTimeEntityEdge> g = createGraph();

        AllDirectedPaths<StationEntity, StopTimeEntityEdge> dijkstraAlg =
                new AllDirectedPaths<>(g);

        List<GraphPath<StationEntity, StopTimeEntityEdge>> iPaths = dijkstraAlg.getAllPaths(fromStation, toStation, true, 10);
        return iPaths;
    }

    public Graph<StationEntity, StopTimeEntityEdge> createGraph() {
        // TODO make static
        Graph<StationEntity, StopTimeEntityEdge> g = new SimpleDirectedGraph<>(StopTimeEntityEdge.class);
        for (TripEntity trip : tripRepository.findAll()) {
            List<StopTimeEntity> stops = trip.getStops();
            for (int i = 0; i < stops.size(); i++) {
                g.addVertex(stops.get(i).getStation());
            }
            for (int i = 0; i < stops.size() - 1; i++) {
                g.addEdge(stops.get(i).getStation(), stops.get(i + 1).getStation(), new StopTimeEntityEdge(stops.get(i), stops.get(i + 1)));
            }
        }
        return g;
    }

    public Itinerary constructItinerary(Itinerary unsafeItinerary) {
        List<Segment> segments = new ArrayList<>();
        for (Segment unsafeSegment : unsafeItinerary.getSegments()) {
            Segment safeSegment = findSegment(unsafeSegment);
            segments.add(safeSegment);
        }
        return new Itinerary(segments);
    }

    public Segment findSegment(Segment segment) {
        return findSegment(segment.getTrip().getId(), segment.getFrom().getId(), segment.getTo().getId(), segment.getDeparture(), segment.getArrival());
    }

    public Segment findSegment(Long tripId, String fromId, String toId, Time departure, Time arrival) {
        Itinerary itinerary = findItinerary(tripId, fromId, toId, departure, arrival);
        Segment segment = itinerary.segments.get(0);
        return segment;
    }

    public Itinerary findItinerary(Long tripId, String fromId, String toId, Time departure, Time arrival) {
        Optional<TripEntity> tripResult = tripRepository.findById(tripId);
        if (!tripResult.isPresent()) {
            throw new IllegalArgumentException();
        }

        TripEntity trip = tripResult.get();

        Optional<StopTimeEntity> fromStopResult = trip.getStops().stream().filter(stop -> stop.getStation().getId().equals(fromId)).findFirst();
        Optional<StopTimeEntity> toStopResult = trip.getStops().stream().filter(stop -> stop.getStation().getId().equals(toId)).findFirst();

        if (!fromStopResult.isPresent() || !toStopResult.isPresent()) {
            throw new IllegalArgumentException();
        }

        StopTimeEntity fromStop = fromStopResult.get();
        StopTimeEntity toStop = toStopResult.get();

        if (!fromStop.getDepartureTime().equals(departure) || !toStop.getArrivalTime().equals(arrival)) {
            throw new IllegalArgumentException();
        }

        return new Itinerary(trip, fromStop, toStop);
    }

    private StationEntity safeGetStationFromId(String id) {
        //TODO Add to document that we are making sure data is clean/valid
        Optional<StationEntity> result = stationRepository.findById(id);

        if (!result.isPresent()) {
            throw new IllegalArgumentException();
        }

        return result.get();
    }

    public Iterable<StationEntity> getAllStations() {
        return stationRepository.findAll();
    }
}
