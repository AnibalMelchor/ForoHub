create table topicos(
            id bigint not null auto_increment,
            titulo varchar(100) not null,
            mensaje varchar(250),
            fecha_creacion datetime not null,
            status varchar(20) not null,
            usuario_id bigint not null,
            curso_id bigint not null,
            respuestas int not null,
            primary key(id),
            constraint fk_topicos_usuarios_id foreign key(usuario_id) references usuarios(id),
            constraint fk_topicos_cursos_id foreign key(curso_id) references curso(id)
);