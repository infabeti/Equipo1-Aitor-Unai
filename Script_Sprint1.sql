create database reto3 collate utf8mb4_spanish_ci;

use reto3;

/*Local cambiado por establecimiento porque local es una palabra reservada en SQL*/

create table establecimiento(
NIF char(9) primary key,
Nombre varchar(40) not null,
Direccion varchar(40) not null,
TipoNegocio enum ('BAR', 'CAFETERIA', 'RESTAURANTE') not null
);

create table actividad(
Transaccion int primary key,
Fecha date not null,
TotalOperacion float not null
);

create table lineaActividad(
NIF char(9) not null,
Transaccion int not null,
constraint pk_lineaActividad primary key (NIF, Transaccion),
constraint fk_NIF foreign key (NIF) references establecimiento (NIF) on update cascade,
constraint fk_Transaccion foreign key (Transaccion) references actividad (Transaccion) on update cascade
);

create table ticket(
Transaccion int primary key,
constraint fk_TransaccionTicket foreign key (Transaccion) references actividad (Transaccion) on update cascade
);

create table comprador(
NIF char(9) primary key,
Nombre varchar(15) not null,
Apellido varchar(20) not null
);

create table factura(
Transaccion int primary key,
NIF char(9) not null,
constraint fk_TransaccionFactura foreign key (Transaccion) references ticket (Transaccion) on update cascade,
constraint fk_NIFComprador foreign key (NIF) references comprador (NIF) on update cascade
);

create table pedido(
Transaccion int primary key,
Domicilio varchar(40),
constraint fk_TransaccionPedido foreign key (Transaccion) references ticket (Transaccion) on update cascade
);

create table producto(
Nombre varchar(30) primary key,
Tipo enum ('BEBIDA', 'COMIDA', 'OTROS')  not null,
FecPed date not null,
PCompra float not null,
PVenta float not null
);

create table LineaProducto(
Nombre varchar(30) not null,
Transaccion int not null,
Cantidad int not null,
PrecioFinal float not null,
TotalProducto float AS (Cantidad * PrecioFinal), /*Atributo calculado*/
constraint PK_LineaProducto primary key(Nombre, Transaccion),
constraint FK_NombreLP foreign key (Nombre) references producto (Nombre) on update cascade,
constraint FK_TransaccionLP foreign key (Transaccion) references ticket (Transaccion) on update cascade
);

create table StockProductoLocal(
Nombre varchar(30) not null,
NIF char(9) not null,
Cantidad int not null,
constraint PK_StockProductoLocal primary key(Nombre, NIF),
constraint FK_NombreStock foreign key (Nombre) references producto (Nombre) on update cascade,
constraint fk_NIFStock foreign key (NIF) references establecimiento (NIF) on update cascade
)