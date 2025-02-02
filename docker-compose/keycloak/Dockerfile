FROM quay.io/keycloak/keycloak:26.1.0

COPY ./oracle-driver/ojdbc11-23.5.0.24.07.jar /opt/keycloak/providers/ojdbc11.jar

# Build Keycloak with Oracle config during IMAGE BUILD
RUN /opt/keycloak/bin/kc.sh build --db=oracle

# Default entrypoint for running Keycloak
ENTRYPOINT ["/opt/keycloak/bin/kc.sh"]