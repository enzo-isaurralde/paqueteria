-- Tabla principal de envíos
CREATE TABLE envios (
                        id                      BIGSERIAL PRIMARY KEY,
                        numero_seguimiento      VARCHAR(30) UNIQUE NOT NULL,
    -- paquete embebido
                        tamanio                 VARCHAR(20) NOT NULL,
                        peso_kg                 NUMERIC(6,2) NOT NULL,
                        largo_cm                NUMERIC(6,2),
                        ancho_cm                NUMERIC(6,2),
                        alto_cm                 NUMERIC(6,2),
                        descripcion             TEXT,
                        fragil                  BOOLEAN DEFAULT FALSE,
    -- zonas
                        zona_origen             VARCHAR(10) NOT NULL,
                        zona_destino            VARCHAR(10) NOT NULL,
                        direccion_origen        VARCHAR(255),
                        direccion_destino       VARCHAR(255),
    -- remitente
                        nombre_remitente        VARCHAR(100),
                        telefono_remitente      VARCHAR(20),
                        email_remitente         VARCHAR(100),
    -- destinatario
                        nombre_destinatario     VARCHAR(100),
                        telefono_destinatario   VARCHAR(20),
                        email_destinatario      VARCHAR(100),
    -- estado y cotización
                        estado                  VARCHAR(20) NOT NULL DEFAULT 'RECIBIDO',
                        monto_cotizado          NUMERIC(10,2),
    -- auditoría
                        fecha_creacion          TIMESTAMP,
                        fecha_ultima_modificacion TIMESTAMP,
                        creado_por              VARCHAR(100),
                        modificado_por          VARCHAR(100)
);

-- Historial de estados
CREATE TABLE historial_estados (
                                   id                BIGSERIAL PRIMARY KEY,
                                   envio_id          BIGINT NOT NULL REFERENCES envios(id),
                                   estado_anterior   VARCHAR(20),
                                   estado_nuevo      VARCHAR(20) NOT NULL,
                                   fecha_cambio      TIMESTAMP,
                                   cambiado_por      VARCHAR(100),
                                   observacion       TEXT
);

-- Tarifas zona a zona
CREATE TABLE tarifas_zona (
                              id              BIGSERIAL PRIMARY KEY,
                              zona_origen     VARCHAR(10) NOT NULL,
                              zona_destino    VARCHAR(10) NOT NULL,
                              precio_base     NUMERIC(10,2) NOT NULL,
                              UNIQUE(zona_origen, zona_destino)
);

-- Carga inicial de tarifas
INSERT INTO tarifas_zona (zona_origen, zona_destino, precio_base) VALUES
                                                                      ('ZONA_1','ZONA_1',1500),('ZONA_1','ZONA_2',2000),('ZONA_1','ZONA_3',2200),
                                                                      ('ZONA_1','ZONA_4',2200),('ZONA_1','ZONA_5',2800),
                                                                      ('ZONA_2','ZONA_1',2000),('ZONA_2','ZONA_2',1500),('ZONA_2','ZONA_3',2500),
                                                                      ('ZONA_2','ZONA_4',2800),('ZONA_2','ZONA_5',3200),
                                                                      ('ZONA_3','ZONA_1',2200),('ZONA_3','ZONA_2',2500),('ZONA_3','ZONA_3',1500),
                                                                      ('ZONA_3','ZONA_4',2500),('ZONA_3','ZONA_5',3000),
                                                                      ('ZONA_4','ZONA_1',2200),('ZONA_4','ZONA_2',2800),('ZONA_4','ZONA_3',2500),
                                                                      ('ZONA_4','ZONA_4',1500),('ZONA_4','ZONA_5',3200),
                                                                      ('ZONA_5','ZONA_1',2800),('ZONA_5','ZONA_2',3200),('ZONA_5','ZONA_3',3000),
                                                                      ('ZONA_5','ZONA_4',3200),('ZONA_5','ZONA_5',1800);

-- Usuarios
CREATE TABLE usuarios (
                          id          BIGSERIAL PRIMARY KEY,
                          username    VARCHAR(50) UNIQUE NOT NULL,
                          password    VARCHAR(255) NOT NULL,
                          email       VARCHAR(100),
                          rol         VARCHAR(20) NOT NULL DEFAULT 'OPERADOR',
                          activo      BOOLEAN DEFAULT TRUE
);