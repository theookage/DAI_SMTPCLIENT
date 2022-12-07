FROM openjdk:8-jre
MAINTAINER Theo Coutaudier <theo.coutaudier@heig-vd.ch>

# Install netcat, so that we can demonstrate container-to-container communication
RUN apt-get update && \
  apt-get -y install netcat && \
  apt-get clean 
  

#
# When we build the image, we copy the executable jar in the image file system. 
#
COPY MockMock.jar /opt/app/server.jar


#
# This is the command that is executed when the Docker container starts
#
CMD ["java", "-jar", "/opt/app/server.jar"]