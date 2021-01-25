create database reto3 collate utf8mb4_spanish_ci;

use reto3;

/*Local cambiado por establecimiento porque local es una palabra reservada en SQL*/

create table establecimiento(
NIF varchar(9) primary key,
Nombre varchar(40) not null,
Direccion varchar(40) not null,
TipoNegocio varchar(15) not null
);

create table actividad(
Transaccion int primary key,
Fecha date not null,
TotalOperacion float not null
);

create table lineaActividad(
NIF varchar(9) not null,
Transaccion int not null,
constraint fk_NIF foreign key (NIF) references establecimiento (NIF) on update cascade,
constraint fk_Transaccion foreign key (Transaccion) references actividad (Transaccion) on update cascade
);

create table ticket(
Transaccion int not null,
constraint fk_TransaccionTicket foreign key (Transaccion) references actividad (Transaccion) on update cascade
);

create table factura(
Transaccion int not null,
NIF varchar(9) not null,
Nombre varchar(15) not null,
Apellido varchar(20) not null,
constraint fk_TransaccionFactura foreign key (Transaccion) references ticket (Transaccion) on update cascade
);

create table pedido(
Transaccion int not null,
Domicilio varchar(40),
constraint fk_TransaccionPedido foreign key (Transaccion) references ticket (Transaccion) on update cascade
);

create table producto(
Nombre varchar(30) primary key,
Tipo varchar(15) not null,
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
constraint FK_NombreLP foreign key (Nombre) references producto (Nombre) on update cascade,
constraint FK_TransaccionLP foreign key (Transaccion) references ticket (Transaccion) on update cascade
);

create table StockProductoLocal(
Nombre varchar(30) not null,
NIF varchar(9) not null,
Cantidad int not null,
constraint FK_NombreStock foreign key (Nombre) references producto (Nombre) on update cascade,
constraint fk_NIFStock foreign key (NIF) references establecimiento (NIF) on update cascade
)