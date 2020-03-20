package edu.drexel.TrainDemo.admin.controllers;

import edu.drexel.TrainDemo.admin.models.PaymentModel;
import edu.drexel.TrainDemo.admin.models.entities.PaymentEntity;
import edu.drexel.TrainDemo.admin.repositories.PaymentRepository;
import edu.drexel.TrainDemo.admin.services.PaymentService;
import edu.drexel.TrainDemo.admin.models.UserModel;
import edu.drexel.TrainDemo.admin.repositories.PaymentRepository;
import edu.drexel.TrainDemo.user.repositories.UserRepository;
import edu.drexel.TrainDemo.user.models.UserEntity;
import edu.drexel.TrainDemo.user.services.UserService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.Iterable;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.io.*; 


@Controller
public class AdminController {
		private PaymentModel paymentModel;
		private PaymentEntity paymentEntity;
		private PaymentRepository paymentRepository;
		private UserRepository userRepository;
		
		@Autowired
		private PaymentService paymentService;

		@Autowired
		private UserService userService;

		private List<String> paymentOptions;
	
		public AdminController(PaymentRepository paymentRepository,
					UserRepository userRepository) {
			this.paymentRepository = paymentRepository;
			this.userRepository = userRepository;
		}

    @GetMapping("/admin")
    public String getAdminPage(@AuthenticationPrincipal OAuth2User principal, Model model) {
        return "admin/admin_portal";
    }

		// Used for debugging
 		@GetMapping("/admin/retrieve_users")
		@ResponseBody
		public Object testEndpoint() {
				return userRepository.findAll();
		}

		// Used for debugging
 		@GetMapping("/admin/reset_payments")
		@ResponseBody
		public Object reset() {
				Iterable<PaymentEntity> options = paymentService.getAllPaymentOptions();
				for (PaymentEntity option: options) {
					if (!option.getName().equals("PayPal")) {
						paymentRepository.delete(option);
					}
				}
			return paymentRepository.findAll();
		}

		@ModelAttribute
		public void getUseCases(Model model) {
			ArrayList<String> useCases = new ArrayList<String>();
			useCases.add("payment");
			useCases.add("user");
			useCases.add("customer");
			useCases.add("groups");
			useCases.add("itineraries");
			useCases.add("orders");
			model.addAttribute("useCases", useCases);
		}
		
		@ModelAttribute
		public void getPaymentModelHeader(Model model) {
			model.addAttribute("paymentModelHeader", "Manage Payments");
		}

		@ModelAttribute
		public void getPaymentForm(Model model) {
    	Iterable<PaymentEntity> options = paymentService.getAllPaymentOptions();
			PaymentEntity paymentEntity = new PaymentEntity();
			PaymentModel paymentModel = new PaymentModel("", options);
			model.addAttribute("paymentModel", paymentModel);
			model.addAttribute("paymentEntity", paymentEntity);
		}

		@PostMapping("/admin/add_payment")
		public String addPaymentOption(@AuthenticationPrincipal OAuth2User principal, @ModelAttribute PaymentEntity payment, Model model) {
			payment.setIsEnabled(true);
			this.paymentService.saveNewPayment(payment);
			getPaymentForm(model);	
			return "admin/admin_portal";
  	}

		@PostMapping("/admin/remove_payment")
		public String removePaymentOption(@AuthenticationPrincipal OAuth2User principal, @ModelAttribute PaymentEntity payment, Model model) {
			payment.setIsEnabled(false);
			paymentService.deletePaymentOption(payment);
			getPaymentForm(model);	
			return "admin/admin_portal";
  	}



		@ModelAttribute
		public void getUserModelHeader(Model model) {
			model.addAttribute("userModelHeader", "Manage Users");
		}
		
		@ModelAttribute
		public void getUserForm(Model model) {
    	Iterable<UserEntity> users = userRepository.findAll();
			UserEntity userEntity = new UserEntity();
			UserModel userModel = new UserModel("", users);
			model.addAttribute("userModel", userModel);
			model.addAttribute("userEntity", userEntity);
		}

		@PostMapping("/admin/update_user")
		public String updateRegisteredUser(@AuthenticationPrincipal OAuth2User principal, @ModelAttribute UserEntity user, Model model) {
			this.userService.updateUser(user);
			getUserForm(model);	
			return "admin/admin_portal";
		}

		@PostMapping("/admin/remove_user")
		public String removeRegisteredUser(@AuthenticationPrincipal OAuth2User principal, @ModelAttribute UserEntity userToRemove, Model model) {
			this.userService.removeUser(userToRemove);
			getUserForm(model);	
			return "admin/admin_portal";
  	}

		@ModelAttribute
		public void getCustomerModel(Model model) {
			System.out.print("test print2");			
			model.addAttribute("customer", "Manage Customer");
		}

		@ModelAttribute
		public void getGroupsModel(Model model) {
			System.out.print("test print3");			
			model.addAttribute("groups", "Manage Groups");
		}

		@ModelAttribute
		public void getItineraryModel(Model model) {
			System.out.print("test print4");			
			model.addAttribute("itineraries", "Manage Itineraries");
		}


		@ModelAttribute
		public void getOrderModel(Model model) {
			System.out.print("test print5");			
			model.addAttribute("orders", "Manage Orders");
		}


//		@ModelAttribute
//		public void getUserInfoModel(Model model) {
//			System.out.print("test print6");			
//			model.addAttribute("user", "Manage Users");
//		}


//		@ModelAttribute
//		public void getUserInfoModel(Model model) {
//			System.out.print("teddst print2");			
//			model.addAttribute("user", "Manage Users");
//		}

}


