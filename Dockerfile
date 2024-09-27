FROM jboss/wildfly:11.0.0.Final

USER root
COPY . .

ADD Docker-files/standalone.xml /opt/jboss/wildfly/standalone/configuration/standalone.xml
ADD Docker-files/sqljdbc4.jar /opt/jboss/wildfly/standalone/deployments/sqljdbc4.jar

RUN touch /opt/jboss/wildfly/standalone/deployments/sqljdbc4.dodeploy

# Añadir un usuario administrador de WildFly
RUN /opt/jboss/wildfly/bin/add-user.sh admin admin --silent

# Añadir el archivo WAR al directorio de despliegue de WildFly
# RUN cp target/*.war /opt/jboss/wildfly/standalone/deployments/
ADD target/servicio-sakai-foto-1.0.war /opt/jboss/wildfly/standalone/deployments/
ADD target/cas.war /opt/jboss/wildfly/standalone/deployments/
# Cambiar el propietario de los archivos de WildFly
EXPOSE 8080 9990