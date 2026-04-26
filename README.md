Esta es una pequeña guia para explicar lo que realice.

El sistema corre aun con el servicio de Spring Boot.
Los enpoints que se toman para realizar laspeticiones son los siguientes:

-http://localhost:8010/api/telefono/listar
-http://localhost:8010/api/telefono/guardar
-http://localhost:8010/api/telefono/buscar/{idTelefono}
-http://localhost:8010/api/telefono/editar/{idTelefono}
-http://localhost:8010/api/telefono/eliminar/{idTelefono}
-http://localhost:8010/api/telefono/contacto/{contactoId}

-http://localhost:8010/api/contacto/listar
-http://localhost:8010/api/contacto/guardar
-http://localhost:8010/api/contacto/editar/{idContacto}
-http://localhost:8010/api/contacto/eliminar/{idContacto}
-http://localhost:8010/api/contacto/buscar/{idContacto}
-http://localhost:8010/api/contacto/buscarNombre/{name}/{apellido}

De iguam manera se comparte la estructura del modelo de db final.

CREATE TABLE CONTACTO(
  ID_CONTACTO NUMBER PRIMARY KEY,
  NAME NVARCHAR2(100) NOT NULL,
  APELLIDO NVARCHAR2(150) NOT NULL,
  EMAIL NVARCHAR2(100) UNIQUE NOT NULL,
  FECHA_CREACION TIMESTAMP DEFAULT SYSTIMESTAMP NOT NULL
);

CREATE SEQUENCE CONTACTO_SEQ
START WITH 2
INCREMENT BY 1;

CREATE TABLE TELEFONO (
    ID_TELEFONO NUMBER PRIMARY KEY,
    TELEFONO_NUM VARCHAR2(25) NOT NULL,
    TIPO NVARCHAR2(20) NOT NULL,
    CONTACTO_ID NUMBER,
    FECHA_CREACION DATE,
    
    CONSTRAINT TIPO_PERMITIDO
        CHECK (TIPO IN ('Casa', 'Celular', 'Trabajo')),

    CONSTRAINT CONTACTO_FK
        FOREIGN KEY (CONTACTO_ID)
        REFERENCES CONTACTO (ID_CONTACTO)
);

CREATE SEQUENCE TELEFONO_SEQ
START WITH 1
INCREMENT BY 1;
