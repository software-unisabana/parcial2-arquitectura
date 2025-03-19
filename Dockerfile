FROM centos:centos7.9.2009
RUN yum install httpd -y
WORKDIR /var/www/html
COPY parcial2-arquitectura-master  .
CMD apachetl -D FOREGROUND