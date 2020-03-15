FROM otrebmuh/dv8-console-wrapper

ARG DV8_KEY=<YOUR-KEY>
ARG DV8_ACTI=<YOUR-ACTI>

COPY DV8_standard_unix_linux-amd64.sh .
COPY dv8_noninteractive_installer.sh .

RUN chmod +x ./DV8_standard_unix_linux-amd64.sh
RUN chmod +x ./dv8_noninteractive_installer.sh
RUN ./dv8_noninteractive_installer.sh

COPY depends.jar .
RUN mkdir output/
RUN exec java -jar depends.jar java /app/ app-json-dep -m=true -s -p=dot --auto-include -d=output/
# Activate Machine
RUN /bin/bash /usr/local/bin/dv8-console license:activate -licenseKey ${DV8_KEY} -activationCode ${DV8_ACTI}
#RUN /usr/local/bin/dv8-console core:core-matrix /output/app-json-dep.json -outputFile /output/app.dv8-dsm
#RUN /usr/local/bin/dv8-console core:convert-matrix /output/app.dv8-dsm
# Deactivate Machine
RUN /bin/bash /usr/local/bin/dv8-console license:deactivate -activationCode ${DV8_ACTI}