create database reto3 collate utf8mb4_spanish_ci;

use reto3;

/*Local cambiado por establecimiento porque local es una palabra reservada en SQL*/

create table establecimiento(
NIF char(9) primary key,
Nombre varchar(40) not null,
Direccion varchar(40) not null,
TipoNegocio enum ('BAR', 'CAFETERIA', 'RESTAURANTE') not null
);


create table empleado(
DNI char(9) primary key,
Nombre varchar(20) unique not null,
Apellido varchar(25) not null,
contrasena varchar(18) not null,
NIF char(9) not null,
constraint fk_NIF_empleado foreign key (NIF) references establecimiento (NIF) on update cascade
);

create table actividad(
Transaccion int primary key,
Fecha date not null,
TotalOperacion float not null,
NIF char(9) not null,
constraint fk_NIF_actividad foreign key (NIF) references establecimiento (NIF) on update cascade
);

create table alimento(
CodigoAlimento int primary key,
Nombre varchar(40) not null,
Tipo enum ('BEBIDA', 'COMIDA', 'OTROS')  not null,
FeCad date,
PCompra float not null default false,
vegetariano boolean not null  default false,
marisco boolean not null default false,
vegano boolean not null default false,
gluten boolean not null default false,
FrutosSecos boolean not null default false
);

create table stock(
NIF char(9) not null,
CodigoAlimento int not null,
cantidad int not null,
constraint pk_stock primary key (NIF, CodigoAlimento),
constraint fk_NIF_stock foreign key (NIF) references establecimiento (NIF) on update cascade,
constraint fk_NIF_CodigoAlimento foreign key (CodigoAlimento) references alimento (CodigoAlimento) on update cascade
);

create table producto(
CodigoAlimento int primary key,
PVenta float not null,
constraint fk_producto_codigoAlimento foreign key (CodigoAlimento) references alimento (CodigoAlimento) on update cascade
);

create table ingrediente(
CodigoAlimento int primary key,
constraint fk_ingrediente_codigoAlimento foreign key (CodigoAlimento) references alimento (CodigoAlimento) on update cascade
);

create table lineaproducto(
CodigoAlimento int,
Transaccion int,
Cantidad int not null,
PrecioFinal float not null,
TotalProducto float AS (Cantidad * PrecioFinal), /*Atributo calculado*/
constraint pk_lineaproducto primary key (Transaccion, CodigoAlimento),
constraint fk_lineaproducto_codigoAlimento foreign key (CodigoAlimento) references producto (CodigoAlimento) on update cascade,
constraint fk_lineaproducto_transaccion foreign key (Transaccion) references actividad (Transaccion) on update cascade
);

create table composicion(
CodigoAlimentoProducto int,
CodigoAlimentoIngrediente int,
cantidad int,
constraint pk_composicion primary key (CodigoAlimentoProducto, CodigoAlimentoIngrediente),
constraint fk_composicion_CodigoAlimentoProducto foreign key (CodigoAlimentoProducto) references producto (CodigoAlimento) on update cascade,
constraint fk_composicion_CodigoAlimentoIngredienteo foreign key (CodigoAlimentoIngrediente) references ingrediente (CodigoAlimento) on update cascade
);

create table comprador(
NIF char(9) primary key,
Nombre varchar(20) not null,
Apellido varchar(25) not null
);

create table factura(
Transaccion int primary key,
NIF char(9),
constraint fk_factura_transaccion foreign key (Transaccion) references actividad (Transaccion) on update cascade,
constraint fk_factura_nif foreign key (NIF) references comprador (NIF) on update cascade
);

create table pedido(
Transaccion int primary key,
domicilio varchar(60),
constraint fk_pedido_transaccion foreign key (Transaccion) references actividad (Transaccion) on update cascade
);

create table fabricante(
CodFabr int primary key,
Nombre varchar(40) not null,
TiempoEntrega int not null
);

create table aprovisionamiento(
Transaccion int primary key,
CodFabr int not null,
constraint fk_aprovisionamiento_transaccion foreign key (Transaccion) references actividad (Transaccion) on update cascade
);


create table comanda(
Transaccion int primary key,
constraint fk_comanda_transaccion foreign key (Transaccion) references actividad (Transaccion) on update cascade
);

create table plato(
codigoplato int primary key,
Nombre varchar(80) not null,
pvp float not null
);

create table lineaplato(
codigoplato int,
Transaccion int,
constraint pk_lineaplato primary key (codigoplato, Transaccion),
constraint fk_lineaplato_codigoplato foreign key (codigoplato) references plato (codigoplato) on update cascade,
constraint fk_lineaplato_transaccion foreign key (Transaccion) references comanda (Transaccion) on update cascade
);

create table lineaingrediente(
codigoplato int,
CodigoAlimento int,
cantidad int,
constraint pk_lineaingrediente primary key (codigoplato, CodigoAlimento),
constraint fk_lineaingrediente_codigoplato foreign key (codigoplato) references plato (codigoplato) on update cascade,
constraint fk_lineaingrediente_CodigoAlimento foreign key (CodigoAlimento) references ingrediente (CodigoAlimento) on update cascade
);

create table suministro(
Transaccion int,
CodigoAlimento int,
constraint pk_suministro primary key (Transaccion, CodigoAlimento),
constraint fk_suministro_CodigoAlimento foreign key (CodigoAlimento) references ingrediente (CodigoAlimento) on update cascade,
constraint fk_suministro_transaccion foreign key (Transaccion) references aprovisionamiento (Transaccion) on update cascade
);