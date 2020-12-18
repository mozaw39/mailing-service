FROM jboss/wildfly
RUN /opt/jboss/wildfly/bin/add-user.sh admin admin --silent
COPY mailingservice.war /opt/jboss/wildfly/standalone/deployments/