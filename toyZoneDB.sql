CREATE DATABASE toyZoneDB;

create table category
(
    id         int identity
        primary key,
    created_at datetime,
    deleted_at datetime,
    updated_at datetime,
    name       nvarchar(255)
)
go

create table role
(
    id         int identity
        primary key,
    created_at datetime,
    deleted_at datetime,
    updated_at datetime,
    name       varchar(255)
)
go

create table [user]
(
    id         int identity
        primary key,
    created_at datetime,
    deleted_at datetime,
    updated_at datetime,
    account    varchar(255),
    email      varchar(255),
    fullname   nvarchar(255),
    otpCode    varchar(255),
    password   varchar(255),
    phone      varchar(255),
    verify     bit,
    role_id    int
        constraint FK_qleu8ddawkdltal07p8e6hgva
            references role
)
go

create table product
(
    id          int identity
        primary key,
    created_at  datetime,
    deleted_at  datetime,
    updated_at  datetime,
    content     nvarchar(2000),
    count       int,
    discount    float,
    image       varchar(255),
    name        nvarchar(255),
    price       numeric(19),
    category_id int
        constraint FK_rlaghtegr0yx2c1q1s6nkqjlh
            references category
)
go

create table [order]
(
    id              int identity
        primary key,
    created_at      datetime,
    deleted_at      datetime,
    updated_at      datetime,
    content         nvarchar(255),
    delivey_address nvarchar(255),
    phone           varchar(255),
    receiver        nvarchar(255),
    status          bit,
    total_money     numeric(19),
    user_id         int
        constraint FK_mh40cn97o5svvy5c32ws9tnvp
            references [user]
)
go


create table product_order
(
    count      int,
    price      numeric(19),
    product_id int not null
        constraint FK_5uw5nxqovigv7mf1gmbos1rl8
            references product,
    odder_id   int not null
        constraint FK_jto6sam1lif6fkov7aiksuj5u
            references [order],
    primary key (product_id, odder_id)
)
go



INSERT INTO toyZoneDB.dbo.role (id, created_at, deleted_at, updated_at, name) VALUES (1, null, null, null, N'admin');
INSERT INTO toyZoneDB.dbo.role (id, created_at, deleted_at, updated_at, name) VALUES (2, null, null, null, N'user');

INSERT INTO toyZoneDB.dbo.[user] (id, created_at, deleted_at, updated_at, account, email, fullname, otpCode, password, phone, verify, role_id) VALUES (4, N'2021-05-29 21:18:25.087', null, N'2021-05-29 21:18:25.087', N'haunguyen', N'hau217vn@gmail.com', N'Hau Nguyen Dep Trai', N'870466', N'123456789', N'0987654321', 1, 2);
INSERT INTO toyZoneDB.dbo.[user] (id, created_at, deleted_at, updated_at, account, email, fullname, otpCode, password, phone, verify, role_id) VALUES (5, N'2021-05-30 17:21:35.387', null, N'2021-05-30 17:21:35.387', N'haung', N'thehau217vn@gmail.com', N'Hau Nguyen', N'413860', N'1234567890', N'0987165454', 1, 1);


INSERT INTO toyZoneDB.dbo.category (id, created_at, deleted_at, updated_at, name) VALUES (1, N'2021-05-22 20:27:00.723', null, N'2021-05-22 20:27:08.007', N'Đồ chơi dã ngoại');
INSERT INTO toyZoneDB.dbo.category (id, created_at, deleted_at, updated_at, name) VALUES (2, N'2021-05-22 20:27:00.723', null, N'2021-05-22 20:27:08.007', N'Đồ dùng nhà bếp');
INSERT INTO toyZoneDB.dbo.category (id, created_at, deleted_at, updated_at, name) VALUES (3, N'2021-05-22 20:27:00.723', null, N'2021-05-22 20:27:08.007', N'Đồ chơi lắp ráp');
INSERT INTO toyZoneDB.dbo.category (id, created_at, deleted_at, updated_at, name) VALUES (4, N'2021-05-22 20:27:00.723', null, N'2021-05-22 20:27:08.007', N'Thủ công');
INSERT INTO toyZoneDB.dbo.category (id, created_at, deleted_at, updated_at, name) VALUES (5, N'2021-05-22 20:27:00.723', null, N'2021-05-22 20:27:08.007', N'Mô hình xe motor');
INSERT INTO toyZoneDB.dbo.category (id, created_at, deleted_at, updated_at, name) VALUES (6, N'2021-05-22 20:27:00.723', null, N'2021-05-22 20:27:08.007', N'Đồ chơi LEGO');
INSERT INTO toyZoneDB.dbo.category (id, created_at, deleted_at, updated_at, name) VALUES (7, N'2021-05-22 20:27:00.723', null, N'2021-05-22 20:27:08.007', N'Xe hơi');
INSERT INTO toyZoneDB.dbo.category (id, created_at, deleted_at, updated_at, name) VALUES (8, N'2021-05-22 20:27:00.723', null, N'2021-05-22 20:27:08.007', N'Xe lửa');
INSERT INTO toyZoneDB.dbo.category (id, created_at, deleted_at, updated_at, name) VALUES (9, N'2021-05-22 20:27:00.723', null, N'2021-05-22 20:27:08.007', N'Xe cứu hỏa');


INSERT INTO toyZoneDB.dbo.product (id, created_at, deleted_at, updated_at, content, count, discount, image, name, price, category_id) VALUES (1, N'2021-05-22 20:27:37.033', null, N'2021-05-22 20:27:37.033', N'', 10, 5, N'https://u6wdnj9wggobj.vcdn.cloud/media/catalog/product/cache/95cb36d3254e0a20b33361b06e7c0ce9/v/e/ve1005z_2.jpg', N'Xe máy xúc', 399000, 1);
INSERT INTO toyZoneDB.dbo.product (id, created_at, deleted_at, updated_at, content, count, discount, image, name, price, category_id) VALUES (2, N'2021-05-22 20:27:37.033', null, N'2021-05-30 16:54:47.537', N'', 40, 15, N'https://u6wdnj9wggobj.vcdn.cloud/media/catalog/product/cache/a237138a07ed0dd2cc8a6fa440635ea6/v/e/ve1002z_3.jpg', N'Xe xe cần cẩu', 399000, 1);
INSERT INTO toyZoneDB.dbo.product (id, created_at, deleted_at, updated_at, content, count, discount, image, name, price, category_id) VALUES (3, N'2021-05-22 20:27:37.033', null, N'2021-05-30 16:28:24.270', N'', 67, 5, N'https://u6wdnj9wggobj.vcdn.cloud/media/catalog/product/cache/a237138a07ed0dd2cc8a6fa440635ea6/magento/BATTAT/VE3000_VE1000Z/VE3000_VE1000Z_1.jpg', N'Xe ben', 399000, 1);
INSERT INTO toyZoneDB.dbo.product (id, created_at, deleted_at, updated_at, content, count, discount, image, name, price, category_id) VALUES (4, N'2021-05-22 20:27:37.033', null, N'2021-05-30 09:36:20.777', N'', 18, 5, N'https://u6wdnj9wggobj.vcdn.cloud/media/catalog/product/cache/a237138a07ed0dd2cc8a6fa440635ea6/7/2/72100_1.jpg', N'Xe điều khiển Mercedes-benz Vàng', 389000, 1);
INSERT INTO toyZoneDB.dbo.product (id, created_at, deleted_at, updated_at, content, count, discount, image, name, price, category_id) VALUES (5, N'2021-05-22 20:27:37.033', null, N'2021-05-30 16:57:30.570', N'', 92, 0.5, N'https://u6wdnj9wggobj.vcdn.cloud/media/catalog/product/cache/7c9924b6276ad76a951c1e786fcf2062/magento/PLAYDOH/E1936/E1936_1.jpg', N'Bộ bột nặn siêu thị vui vẻ', 599000, 2);
INSERT INTO toyZoneDB.dbo.product (id, created_at, deleted_at, updated_at, content, count, discount, image, name, price, category_id) VALUES (6, N'2021-05-22 20:27:37.033', null, N'2021-05-30 16:57:30.847', N'', 116, 0.5, N'https://u6wdnj9wggobj.vcdn.cloud/media/catalog/product/cache/7c9924b6276ad76a951c1e786fcf2062/magento/PLAYDOH/B9014/B9014_1.jpg', N'Bếp nấu tiện lợi', 380000, 2);
INSERT INTO toyZoneDB.dbo.product (id, created_at, deleted_at, updated_at, content, count, discount, image, name, price, category_id) VALUES (7, N'2021-05-22 20:27:37.033', null, N'2021-05-30 16:57:30.713', N'', 127, 0.5, N'https://u6wdnj9wggobj.vcdn.cloud/media/catalog/product/cache/7c9924b6276ad76a951c1e786fcf2062/magento/PLAYDOH/B9012/B9012_1.jpg', N'Khuôn tạo hình rau củ nhà bếp', 227000, 2);
INSERT INTO toyZoneDB.dbo.product (id, created_at, deleted_at, updated_at, content, count, discount, image, name, price, category_id) VALUES (8, N'2021-05-22 20:27:37.033', null, N'2021-05-30 16:57:30.387', N'', 137, 0.5, N'https://u6wdnj9wggobj.vcdn.cloud/media/catalog/product/cache/7c9924b6276ad76a951c1e786fcf2062/magento/PLAYDOH/B1856/B1856_1.jpg', N'Bánh pizza', 190000, 2);
INSERT INTO toyZoneDB.dbo.product (id, created_at, deleted_at, updated_at, content, count, discount, image, name, price, category_id) VALUES (9, N'2021-05-22 20:27:37.033', null, N'2021-05-22 20:27:37.033', N'', 18, 0.5, N'https://u6wdnj9wggobj.vcdn.cloud/media/catalog/product/cache/7c9924b6276ad76a951c1e786fcf2062/magento/SUPERWINGS/YW710320/YW710320_1.jpg', N'Máy bay lắp ráp - Donnie Thông Minh', 399000, 3);
INSERT INTO toyZoneDB.dbo.product (id, created_at, deleted_at, updated_at, content, count, discount, image, name, price, category_id) VALUES (10, N'2021-05-22 20:27:37.033', null, N'2021-05-22 20:27:37.033', N'', 120, 0.5, N'https://u6wdnj9wggobj.vcdn.cloud/media/catalog/product/cache/7c9924b6276ad76a951c1e786fcf2062/magento/SUPERWINGS/YW710310/YW710310_1.jpg', N'Máy bay lắp ráp - Jett Tia Chớp', 399000, 3);
INSERT INTO toyZoneDB.dbo.product (id, created_at, deleted_at, updated_at, content, count, discount, image, name, price, category_id) VALUES (11, N'2021-05-22 20:27:37.033', null, N'2021-05-22 20:27:37.033', N'', 105, 0.5, N'https://u6wdnj9wggobj.vcdn.cloud/media/catalog/product/cache/7c9924b6276ad76a951c1e786fcf2062/magento/MAISTO/MT39189/MT39189_1.jpg', N'Đồ chơi lắp ráp xe 1:12 Ducati Monster 696', 399000, 3);
INSERT INTO toyZoneDB.dbo.product (id, created_at, deleted_at, updated_at, content, count, discount, image, name, price, category_id) VALUES (12, N'2021-05-22 20:27:37.033', null, N'2021-05-22 20:27:37.033', N'', 105, 0.5, N'https://u6wdnj9wggobj.vcdn.cloud/media/catalog/product/cache/7c9924b6276ad76a951c1e786fcf2062/magento/MAISTO/MT39154/MT39154_2.jpg', N'Đồ chơi lắp ráp xe 1:12 KAWASAKI NINJA ZX-6R', 399000, 3);
INSERT INTO toyZoneDB.dbo.product (id, created_at, deleted_at, updated_at, content, count, discount, image, name, price, category_id) VALUES (13, N'2021-05-22 20:27:37.033', null, N'2021-05-22 20:27:37.033', N'', 100, 0.5, N'https://u6wdnj9wggobj.vcdn.cloud/media/catalog/product/cache/7c9924b6276ad76a951c1e786fcf2062/magento/PLAYDOH/23414/23414_9.jpg', N'Thế giới sắc màu', 399000, 4);
INSERT INTO toyZoneDB.dbo.product (id, created_at, deleted_at, updated_at, content, count, discount, image, name, price, category_id) VALUES (14, N'2021-05-22 20:27:37.033', null, N'2021-05-22 20:27:37.033', N'', 100, 0.5, N'https://u6wdnj9wggobj.vcdn.cloud/media/catalog/product/cache/7c9924b6276ad76a951c1e786fcf2062/magento/PLAYDOH/22735/22735_1.jpg', N'Dụng cụ ngộ nghĩnh', 399000, 4);
INSERT INTO toyZoneDB.dbo.product (id, created_at, deleted_at, updated_at, content, count, discount, image, name, price, category_id) VALUES (15, N'2021-05-22 20:27:37.033', null, N'2021-05-22 20:27:37.033', N'', 98, 0.5, N'https://u6wdnj9wggobj.vcdn.cloud/media/catalog/product/cache/7c9924b6276ad76a951c1e786fcf2062/2/2/22037_2.jpg', N'Ống bột nặn 10 màu', 399000, 4);
INSERT INTO toyZoneDB.dbo.product (id, created_at, deleted_at, updated_at, content, count, discount, image, name, price, category_id) VALUES (16, N'2021-05-22 20:27:37.033', null, N'2021-05-22 20:27:37.033', N'', 85, 0.5, N'https://u6wdnj9wggobj.vcdn.cloud/media/catalog/product/cache/7c9924b6276ad76a951c1e786fcf2062/magento/PLAYDOH/22825/22825_1.jpg', N'Bộ dụng cụ thần kỳ', 69000, 4);
INSERT INTO toyZoneDB.dbo.product (id, created_at, deleted_at, updated_at, content, count, discount, image, name, price, category_id) VALUES (17, N'2021-05-22 20:27:37.033', null, N'2021-05-22 20:27:37.033', N'', 100, 15, N'https://u6wdnj9wggobj.vcdn.cloud/media/catalog/product/cache/7c9924b6276ad76a951c1e786fcf2062/magento/MAISTO/MT39092/MT39092_1.jpg', N'Đồ chơi lắp ráp xe 1:12 HONDA CBR 1000 RR', 349000, 5);
INSERT INTO toyZoneDB.dbo.product (id, created_at, deleted_at, updated_at, content, count, discount, image, name, price, category_id) VALUES (18, N'2021-05-22 20:27:37.033', null, N'2021-05-22 20:27:37.033', N'', 100, 5, N'https://u6wdnj9wggobj.vcdn.cloud/media/catalog/product/cache/7c9924b6276ad76a951c1e786fcf2062/magento/MAISTO/MT39051AL_39196/MT39051AL_39196_1.jpg', N'Đồ chơi xe mô tô lắp ráp Ducati Diavel Carbon', 349000, 5);
INSERT INTO toyZoneDB.dbo.product (id, created_at, deleted_at, updated_at, content, count, discount, image, name, price, category_id) VALUES (19, N'2021-05-22 20:27:37.033', null, N'2021-05-22 20:27:37.033', N'', 100, 5, N'https://u6wdnj9wggobj.vcdn.cloud/media/catalog/product/cache/7c9924b6276ad76a951c1e786fcf2062/magento/MAISTO/MT39154/MT39154_2.jpg', N'Đồ chơi lắp ráp xe 1:12 Honda CBR600RR', 349000, 5);
INSERT INTO toyZoneDB.dbo.product (id, created_at, deleted_at, updated_at, content, count, discount, image, name, price, category_id) VALUES (20, N'2021-05-22 20:27:37.033', null, N'2021-05-22 20:27:37.033', N'', 100, 5, N'https://u6wdnj9wggobj.vcdn.cloud/media/catalog/product/cache/7c9924b6276ad76a951c1e786fcf2062/m/t/mt39051al_39197_1_1_.jpg', N'Đồ chơi xe mô tô lắp ráp Kawasaki Ninja ZX 14R', 349000, 5);
INSERT INTO toyZoneDB.dbo.product (id, created_at, deleted_at, updated_at, content, count, discount, image, name, price, category_id) VALUES (21, N'2021-05-22 20:27:37.033', null, N'2021-05-22 20:27:37.033', N'', 150, 20, N'https://u6wdnj9wggobj.vcdn.cloud/media/catalog/product/cache/a237138a07ed0dd2cc8a6fa440635ea6/0/6/06_130.jpg', N'Hộp LEGO Classic sáng tạo', 385000, 6);
INSERT INTO toyZoneDB.dbo.product (id, created_at, deleted_at, updated_at, content, count, discount, image, name, price, category_id) VALUES (22, N'2021-05-22 20:27:37.033', null, N'2021-05-22 20:27:37.033', N'', 130, 5, N'https://u6wdnj9wggobj.vcdn.cloud/media/catalog/product/cache/a237138a07ed0dd2cc8a6fa440635ea6/0/4/04_92.jpg', N'Vali LEGO Classic Sáng Tạo', 419000, 6);
INSERT INTO toyZoneDB.dbo.product (id, created_at, deleted_at, updated_at, content, count, discount, image, name, price, category_id) VALUES (23, N'2021-05-22 20:27:37.033', null, N'2021-05-22 20:27:37.033', N'', 130, 5, N'https://u6wdnj9wggobj.vcdn.cloud/media/catalog/product/cache/a237138a07ed0dd2cc8a6fa440635ea6/0/8/08_185_1.jpg', N'Xe Tải Tên Lửa', 644000, 6);
INSERT INTO toyZoneDB.dbo.product (id, created_at, deleted_at, updated_at, content, count, discount, image, name, price, category_id) VALUES (24, N'2021-05-22 20:27:37.033', null, N'2021-05-22 20:27:37.033', N'', 120, 40, N'https://u6wdnj9wggobj.vcdn.cloud/media/catalog/product/cache/a237138a07ed0dd2cc8a6fa440635ea6/6/0/60270_9.jpg', N'Thùng Gạch Cảnh Sát', 1339000, 6);
INSERT INTO toyZoneDB.dbo.product (id, created_at, deleted_at, updated_at, content, count, discount, image, name, price, category_id) VALUES (25, N'2021-05-22 20:27:37.033', null, N'2021-05-22 20:27:37.033', N'', 120, 30, N'https://u6wdnj9wggobj.vcdn.cloud/media/catalog/product/cache/7c9924b6276ad76a951c1e786fcf2062/magento/SIKU/1435/1435_2.png', N'Xe Dodge Charger', 129000, 7);
INSERT INTO toyZoneDB.dbo.product (id, created_at, deleted_at, updated_at, content, count, discount, image, name, price, category_id) VALUES (26, N'2021-05-22 20:27:37.033', null, N'2021-05-22 20:27:37.033', N'', 130, 35, N'https://u6wdnj9wggobj.vcdn.cloud/media/catalog/product/cache/7c9924b6276ad76a951c1e786fcf2062/1/4/1434.1.jpg', N'Xe Dodge Viper', 129000, 7);
INSERT INTO toyZoneDB.dbo.product (id, created_at, deleted_at, updated_at, content, count, discount, image, name, price, category_id) VALUES (27, N'2021-05-22 20:27:37.033', null, N'2021-05-22 20:27:37.033', N'', 160, 35, N'https://u6wdnj9wggobj.vcdn.cloud/media/catalog/product/cache/7c9924b6276ad76a951c1e786fcf2062/magento/SIKU/1432/1432_2.jpg', N'Xe BMW X5', 129000, 7);
INSERT INTO toyZoneDB.dbo.product (id, created_at, deleted_at, updated_at, content, count, discount, image, name, price, category_id) VALUES (28, N'2021-05-22 20:27:37.033', null, N'2021-05-22 20:27:37.033', N'', 150, 50, N'https://u6wdnj9wggobj.vcdn.cloud/media/catalog/product/cache/7c9924b6276ad76a951c1e786fcf2062/1/4/1430.1.jpg', N'Xe Audi R8', 129000, 7);
INSERT INTO toyZoneDB.dbo.product (id, created_at, deleted_at, updated_at, content, count, discount, image, name, price, category_id) VALUES (29, N'2021-05-22 20:27:37.033', null, N'2021-05-22 20:27:37.033', N'', 1670, 0.5, N'https://u6wdnj9wggobj.vcdn.cloud/media/catalog/product/cache/7c9924b6276ad76a951c1e786fcf2062/magento/LEGO_CITY/60052/60052_1.jpg', N'Xe Lửa Vận Tải', 399000, 8);
INSERT INTO toyZoneDB.dbo.product (id, created_at, deleted_at, updated_at, content, count, discount, image, name, price, category_id) VALUES (30, N'2021-05-22 20:27:37.033', null, N'2021-05-22 20:27:37.033', N'', 1560, 0.5, N'https://u6wdnj9wggobj.vcdn.cloud/media/catalog/product/cache/7c9924b6276ad76a951c1e786fcf2062/magento/LEGO_CITY/60051/60051_1.jpg', N'Xe Lửa Siêu Tốc', 399000, 8);
INSERT INTO toyZoneDB.dbo.product (id, created_at, deleted_at, updated_at, content, count, discount, image, name, price, category_id) VALUES (31, N'2021-05-22 20:27:37.033', null, N'2021-05-22 20:27:37.033', N'', 150, 0.5, N'https://u6wdnj9wggobj.vcdn.cloud/media/catalog/product/cache/7c9924b6276ad76a951c1e786fcf2062/magento/LEGO_CITY/60050/60050_1.jpg', N'Trạm Xe Lửa', 399000, 8);
INSERT INTO toyZoneDB.dbo.product (id, created_at, deleted_at, updated_at, content, count, discount, image, name, price, category_id) VALUES (32, N'2021-05-22 20:27:37.033', null, N'2021-05-22 20:27:37.033', N'', 100, 0.5, N'https://u6wdnj9wggobj.vcdn.cloud/media/catalog/product/cache/7c9924b6276ad76a951c1e786fcf2062/magento/EDUCATION/45008/45008_1.jpg', N'Xe máy xúc', 399000, 8);
INSERT INTO toyZoneDB.dbo.product (id, created_at, deleted_at, updated_at, content, count, discount, image, name, price, category_id) VALUES (33, N'2021-05-22 20:27:37.033', null, N'2021-05-22 20:27:37.033', N'', 130, 0, N'https://u6wdnj9wggobj.vcdn.cloud/media/catalog/product/cache/a237138a07ed0dd2cc8a6fa440635ea6/v/e/ve1004z_2.jpg', N'Xe cứu hỏa', 399000, 9);
INSERT INTO toyZoneDB.dbo.product (id, created_at, deleted_at, updated_at, content, count, discount, image, name, price, category_id) VALUES (34, N'2021-05-22 20:27:37.033', null, N'2021-05-22 20:27:37.033', N'', 110, 5, N'https://u6wdnj9wggobj.vcdn.cloud/media/catalog/product/cache/a237138a07ed0dd2cc8a6fa440635ea6/f/_/f_03590_22.jpg', N'Đồ chơi dạng mô hình theo tỷ lệ thu nhỏ 1:16 xe cứu hỏa than', 2999000, 9);
INSERT INTO toyZoneDB.dbo.product (id, created_at, deleted_at, updated_at, content, count, discount, image, name, price, category_id) VALUES (35, N'2021-05-22 20:27:37.033', null, N'2021-05-22 20:27:37.033', N'', 150, 5, N'https://u6wdnj9wggobj.vcdn.cloud/media/catalog/product/cache/a237138a07ed0dd2cc8a6fa440635ea6/b/r/bru02532_8_.jpg', N'Đồ chơi dạng mô hình theo tỷ lệ thu nhỏ 1:16', 1599000, 9);
INSERT INTO toyZoneDB.dbo.product (id, created_at, deleted_at, updated_at, content, count, discount, image, name, price, category_id) VALUES (36, N'2021-05-22 20:27:37.033', null, N'2021-05-22 20:27:37.033', N'', 150, 50, N'https://u6wdnj9wggobj.vcdn.cloud/media/catalog/product/cache/a237138a07ed0dd2cc8a6fa440635ea6/magento/LEGO_CITY/60061/60061_1.jpg', N'Xe Cứu Hỏa Sân Bay', 1209000, 9);


INSERT INTO toyZoneDB.dbo.[order] (id, created_at, deleted_at, updated_at, content, delivey_address, phone, receiver, status, total_money, user_id) VALUES (1, N'2021-05-29 22:21:08.330', null, N'2021-05-29 22:21:08.330', N'hahaha', N'hahaah', N'0987654321', N'Hau Nguyen', 0, 596005, 4);
INSERT INTO toyZoneDB.dbo.[order] (id, created_at, deleted_at, updated_at, content, delivey_address, phone, receiver, status, total_money, user_id) VALUES (2, N'2021-05-29 23:06:06.273', null, N'2021-05-29 23:06:06.273', N'hahaha', N'hahaah', N'0987654321', N'Hau Nguyen', 0, 369550, 4);
INSERT INTO toyZoneDB.dbo.[order] (id, created_at, deleted_at, updated_at, content, delivey_address, phone, receiver, status, total_money, user_id) VALUES (3, N'2021-05-29 23:11:33.007', null, N'2021-05-29 23:11:33.007', N'hahaha', N'hahaah', N'0987654321', N'Hau Nguyen', 0, 379050, 4);
INSERT INTO toyZoneDB.dbo.[order] (id, created_at, deleted_at, updated_at, content, delivey_address, phone, receiver, status, total_money, user_id) VALUES (4, N'2021-05-29 23:13:59.497', null, N'2021-05-29 23:13:59.497', N'hahaha', N'hahaah', N'0987654321', N'Hau Nguyen', 0, 596005, 4);
INSERT INTO toyZoneDB.dbo.[order] (id, created_at, deleted_at, updated_at, content, delivey_address, phone, receiver, status, total_money, user_id) VALUES (5, N'2021-05-29 23:20:43.820', null, N'2021-05-29 23:20:43.820', N'hahaha', N'hahaah', N'0987654321', N'Hau Nguyen', 0, 339150, 4);
INSERT INTO toyZoneDB.dbo.[order] (id, created_at, deleted_at, updated_at, content, delivey_address, phone, receiver, status, total_money, user_id) VALUES (6, N'2021-05-29 23:23:06.317', null, N'2021-05-29 23:23:06.317', N'hahaha', N'hahaah', N'0987654321', N'Hau Nguyen', 0, 339150, 4);
INSERT INTO toyZoneDB.dbo.[order] (id, created_at, deleted_at, updated_at, content, delivey_address, phone, receiver, status, total_money, user_id) VALUES (7, N'2021-05-29 23:44:34.827', null, N'2021-05-29 23:44:34.827', N'hahaha', N'hahaah', N'0987654321', N'Hau Nguyen', 0, 339150, 4);
INSERT INTO toyZoneDB.dbo.[order] (id, created_at, deleted_at, updated_at, content, delivey_address, phone, receiver, status, total_money, user_id) VALUES (8, N'2021-05-29 23:46:18.657', null, N'2021-05-29 23:46:18.657', N'hahaha', N'hahaah', N'0987654321', N'Hau Nguyen', 0, 339150, 4);
INSERT INTO toyZoneDB.dbo.[order] (id, created_at, deleted_at, updated_at, content, delivey_address, phone, receiver, status, total_money, user_id) VALUES (9, N'2021-05-29 23:49:54.767', null, N'2021-05-29 23:49:54.767', N'hahaha', N'hahaah', N'0987654321', N'Hau Nguyen', 1, 379050, 4);
INSERT INTO toyZoneDB.dbo.[order] (id, created_at, deleted_at, updated_at, content, delivey_address, phone, receiver, status, total_money, user_id) VALUES (10, N'2021-05-30 09:20:07.970', null, N'2021-05-30 09:20:07.970', N'hahaha', N'hahaah', N'0987654321', N'Hau Nguyen', 0, 596005, 4);
INSERT INTO toyZoneDB.dbo.[order] (id, created_at, deleted_at, updated_at, content, delivey_address, phone, receiver, status, total_money, user_id) VALUES (11, N'2021-05-30 09:36:20.553', null, N'2021-05-30 09:36:20.553', N'hahaha', N'hahaah', N'0987654321', N'Hau Nguyen', 0, 369550, 4);
INSERT INTO toyZoneDB.dbo.[order] (id, created_at, deleted_at, updated_at, content, delivey_address, phone, receiver, status, total_money, user_id) VALUES (12, N'2021-05-30 16:27:02.657', null, N'2021-05-30 16:27:02.657', N'hahaha', N'hahaah', N'0987654321', N'Hau Nguyen', 0, 339150, 4);
INSERT INTO toyZoneDB.dbo.[order] (id, created_at, deleted_at, updated_at, content, delivey_address, phone, receiver, status, total_money, user_id) VALUES (13, N'2021-05-30 16:28:24.067', null, N'2021-05-30 16:28:24.067', N'hahaha', N'hahaah', N'0987654321', N'Hau Nguyen', 0, 1057350, 4);
INSERT INTO toyZoneDB.dbo.[order] (id, created_at, deleted_at, updated_at, content, delivey_address, phone, receiver, status, total_money, user_id) VALUES (14, N'2021-05-30 16:33:47.917', null, N'2021-05-30 16:33:47.917', N'hahaha', N'hahaah', N'0987654321', N'Hau Nguyen', 0, 678300, 4);
INSERT INTO toyZoneDB.dbo.[order] (id, created_at, deleted_at, updated_at, content, delivey_address, phone, receiver, status, total_money, user_id) VALUES (15, N'2021-05-30 16:35:07.583', null, N'2021-05-30 16:35:07.583', N'hahaha', N'hahaah', N'0987654321', N'Hau Nguyen', 0, 1163155, 4);
INSERT INTO toyZoneDB.dbo.[order] (id, created_at, deleted_at, updated_at, content, delivey_address, phone, receiver, status, total_money, user_id) VALUES (16, N'2021-05-30 16:51:25.277', null, N'2021-05-30 16:51:25.277', N'hahaha', N'hahaah', N'0987654321', N'Hau Nguyen', 0, 596005, 4);
INSERT INTO toyZoneDB.dbo.[order] (id, created_at, deleted_at, updated_at, content, delivey_address, phone, receiver, status, total_money, user_id) VALUES (17, N'2021-05-30 16:54:47.417', null, N'2021-05-30 16:54:47.417', N'hahaha', N'hahaah', N'0987654321', N'Hau Nguyen', 0, 339150, 4);
INSERT INTO toyZoneDB.dbo.[order] (id, created_at, deleted_at, updated_at, content, delivey_address, phone, receiver, status, total_money, user_id) VALUES (18, N'2021-05-30 16:56:38.430', null, N'2021-05-30 16:56:38.430', N'hahaha', N'hahaah', N'0987654321', N'Hau Nguyen', 0, 596005, 4);
INSERT INTO toyZoneDB.dbo.[order] (id, created_at, deleted_at, updated_at, content, delivey_address, phone, receiver, status, total_money, user_id) VALUES (19, N'2021-05-30 16:57:30.040', null, N'2021-05-30 16:57:30.040', N'hahaha', N'hahaah', N'0987654321', N'Hau Nguyen', 1, 3382005, 4);


INSERT INTO toyZoneDB.dbo.product_order (count, price, product_id, odder_id) VALUES (1, 339150, 2, 5);
INSERT INTO toyZoneDB.dbo.product_order (count, price, product_id, odder_id) VALUES (1, 339150, 2, 6);
INSERT INTO toyZoneDB.dbo.product_order (count, price, product_id, odder_id) VALUES (1, 339150, 2, 7);
INSERT INTO toyZoneDB.dbo.product_order (count, price, product_id, odder_id) VALUES (1, 339150, 2, 8);
INSERT INTO toyZoneDB.dbo.product_order (count, price, product_id, odder_id) VALUES (1, 339150, 2, 12);
INSERT INTO toyZoneDB.dbo.product_order (count, price, product_id, odder_id) VALUES (2, 678300, 2, 13);
INSERT INTO toyZoneDB.dbo.product_order (count, price, product_id, odder_id) VALUES (2, 678300, 2, 14);
INSERT INTO toyZoneDB.dbo.product_order (count, price, product_id, odder_id) VALUES (1, 339150, 2, 17);
INSERT INTO toyZoneDB.dbo.product_order (count, price, product_id, odder_id) VALUES (1, 379050, 3, 3);
INSERT INTO toyZoneDB.dbo.product_order (count, price, product_id, odder_id) VALUES (1, 379050, 3, 9);
INSERT INTO toyZoneDB.dbo.product_order (count, price, product_id, odder_id) VALUES (1, 379050, 3, 13);
INSERT INTO toyZoneDB.dbo.product_order (count, price, product_id, odder_id) VALUES (1, 369550, 4, 2);
INSERT INTO toyZoneDB.dbo.product_order (count, price, product_id, odder_id) VALUES (1, 369550, 4, 11);
INSERT INTO toyZoneDB.dbo.product_order (count, price, product_id, odder_id) VALUES (1, 596005, 5, 1);
INSERT INTO toyZoneDB.dbo.product_order (count, price, product_id, odder_id) VALUES (1, 596005, 5, 4);
INSERT INTO toyZoneDB.dbo.product_order (count, price, product_id, odder_id) VALUES (1, 596005, 5, 10);
INSERT INTO toyZoneDB.dbo.product_order (count, price, product_id, odder_id) VALUES (1, 596005, 5, 15);
INSERT INTO toyZoneDB.dbo.product_order (count, price, product_id, odder_id) VALUES (1, 596005, 5, 16);
INSERT INTO toyZoneDB.dbo.product_order (count, price, product_id, odder_id) VALUES (1, 596005, 5, 18);
INSERT INTO toyZoneDB.dbo.product_order (count, price, product_id, odder_id) VALUES (2, 1192010, 5, 19);
INSERT INTO toyZoneDB.dbo.product_order (count, price, product_id, odder_id) VALUES (1, 378100, 6, 15);
INSERT INTO toyZoneDB.dbo.product_order (count, price, product_id, odder_id) VALUES (3, 1134300, 6, 19);
INSERT INTO toyZoneDB.dbo.product_order (count, price, product_id, odder_id) VALUES (3, 677595, 7, 19);
INSERT INTO toyZoneDB.dbo.product_order (count, price, product_id, odder_id) VALUES (1, 189050, 8, 15);
INSERT INTO toyZoneDB.dbo.product_order (count, price, product_id, odder_id) VALUES (2, 378100, 8, 19);




















