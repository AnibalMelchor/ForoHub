create table usuarios(
            id bigint not null auto_increment,
            nombre varchar(100) not null,
            correoElectronico varchar(50),
            contrasena varchar(255) not null,
            activo tinyint not null,
            primary key(id)
);