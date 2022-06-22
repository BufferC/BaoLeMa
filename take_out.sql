create database take_out default charset = utf8;

use take_out;

create table address_book
(
    id            bigint                       not null comment '主键'
        primary key,
    user_id       bigint                       not null comment '用户id',
    consignee     varchar(50)                  not null comment '收货人',
    sex           tinyint                      not null comment '性别 0 女 1 男',
    phone         varchar(11)                  not null comment '手机号',
    province_code varchar(12) charset utf8mb4  null comment '省级区划编号',
    province_name varchar(32) charset utf8mb4  null comment '省级名称',
    city_code     varchar(12) charset utf8mb4  null comment '市级区划编号',
    city_name     varchar(32) charset utf8mb4  null comment '市级名称',
    district_code varchar(12) charset utf8mb4  null comment '区级区划编号',
    district_name varchar(32) charset utf8mb4  null comment '区级名称',
    detail        varchar(200) charset utf8mb4 null comment '详细地址',
    label         varchar(100) charset utf8mb4 null comment '标签',
    is_default    tinyint(1) default 0         not null comment '默认 0 否 1是',
    create_time   datetime                     not null comment '创建时间',
    update_time   datetime                     not null comment '更新时间',
    create_user   bigint                       not null comment '创建人',
    update_user   bigint                       not null comment '修改人',
    is_deleted    int        default 0         not null comment '是否删除'
)
    comment '地址管理' collate = utf8_bin;

INSERT INTO take_out.address_book (id, user_id, consignee, sex, phone, province_code, province_name, city_code, city_name, district_code, district_name, detail, label, is_default, create_time, update_time, create_user, update_user, is_deleted) VALUES (1417414526093082626, 1417012167126876162, '小明', 1, '13812345678', null, null, null, null, null, null, '昌平区金燕龙办公楼', '公司', 1, '2021-07-20 17:22:12', '2021-07-20 17:26:33', 1417012167126876162, 1417012167126876162, 0);
INSERT INTO take_out.address_book (id, user_id, consignee, sex, phone, province_code, province_name, city_code, city_name, district_code, district_name, detail, label, is_default, create_time, update_time, create_user, update_user, is_deleted) VALUES (1417414926166769666, 1417012167126876162, '小李', 1, '13512345678', null, null, null, null, null, null, '测试', '家', 0, '2021-07-20 17:23:47', '2021-07-20 17:23:47', 1417012167126876162, 1417012167126876162, 0);
INSERT INTO take_out.address_book (id, user_id, consignee, sex, phone, province_code, province_name, city_code, city_name, district_code, district_name, detail, label, is_default, create_time, update_time, create_user, update_user, is_deleted) VALUES (1538003584363298817, 1538003320973590529, '任敏', 0, '18297478590', null, null, null, null, null, null, '内蒙古自治区兴安盟扎赉特旗八一牧场', '公司', 0, '2022-06-18 11:40:04', '2022-06-19 18:40:55', 1538003320973590529, 1538003320973590529, 0);
INSERT INTO take_out.address_book (id, user_id, consignee, sex, phone, province_code, province_name, city_code, city_name, district_code, district_name, detail, label, is_default, create_time, update_time, create_user, update_user, is_deleted) VALUES (1538003695206170626, 1538003320973590529, '龙强', 1, '14710140960', null, null, null, null, null, null, '重庆市市辖区开州区正安街道', '家', 1, '2022-06-18 11:40:30', '2022-06-19 18:40:56', 1538003320973590529, 1538003320973590529, 0);

create table category
(
    id          bigint        not null comment '主键'
        primary key,
    type        int           null comment '类型   1 菜品分类 2 套餐分类',
    name        varchar(64)   not null comment '分类名称',
    sort        int default 0 not null comment '顺序',
    create_time datetime      not null comment '创建时间',
    update_time datetime      not null comment '更新时间',
    create_user bigint        not null comment '创建人',
    update_user bigint        not null comment '修改人',
    constraint idx_category_name
        unique (name)
)
    comment '菜品及套餐分类' collate = utf8_bin;

INSERT INTO take_out.category (id, type, name, sort, create_time, update_time, create_user, update_user) VALUES (1397844263642378242, 1, '湘菜', 2, '2021-05-27 09:16:58', '2021-07-15 20:25:23', 1, 1);
INSERT INTO take_out.category (id, type, name, sort, create_time, update_time, create_user, update_user) VALUES (1397844303408574465, 1, '川菜', 3, '2021-05-27 09:17:07', '2021-06-02 14:27:22', 1, 1);
INSERT INTO take_out.category (id, type, name, sort, create_time, update_time, create_user, update_user) VALUES (1397844391040167938, 1, '粤菜', 4, '2021-05-27 09:17:28', '2021-07-09 14:37:13', 1, 1);
INSERT INTO take_out.category (id, type, name, sort, create_time, update_time, create_user, update_user) VALUES (1413341197421846529, 1, '饮品', 11, '2021-07-09 11:36:15', '2021-07-09 14:39:15', 1, 1);
INSERT INTO take_out.category (id, type, name, sort, create_time, update_time, create_user, update_user) VALUES (1413342269393674242, 2, '商务套餐', 5, '2021-07-09 11:40:30', '2021-07-09 14:43:45', 1, 1);
INSERT INTO take_out.category (id, type, name, sort, create_time, update_time, create_user, update_user) VALUES (1413384954989060097, 1, '主食', 12, '2021-07-09 14:30:07', '2021-07-09 14:39:19', 1, 1);
INSERT INTO take_out.category (id, type, name, sort, create_time, update_time, create_user, update_user) VALUES (1413386191767674881, 2, '儿童套餐', 6, '2021-07-09 14:35:02', '2021-07-09 14:39:05', 1, 1);
INSERT INTO take_out.category (id, type, name, sort, create_time, update_time, create_user, update_user) VALUES (1537622365431660546, 1, '推荐', 0, '2022-06-17 10:25:14', '2022-06-17 10:38:48', 1, 1);

create table dish
(
    id          bigint         not null comment '主键'
        primary key,
    name        varchar(64)    not null comment '菜品名称',
    category_id bigint         not null comment '菜品分类id',
    price       decimal(10, 2) null comment '菜品价格',
    code        varchar(64)    not null comment '商品码',
    image       varchar(200)   not null comment '图片',
    description varchar(400)   null comment '描述信息',
    status      int default 1  not null comment '0 停售 1 起售',
    sort        int default 0  not null comment '顺序',
    create_time datetime       not null comment '创建时间',
    update_time datetime       not null comment '更新时间',
    create_user bigint         not null comment '创建人',
    update_user bigint         not null comment '修改人',
    is_deleted  int default 0  not null comment '是否删除',
    constraint idx_dish_name
        unique (name)
)
    comment '菜品管理' collate = utf8_bin;

INSERT INTO take_out.dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397849739276890114, '辣子鸡', 1397844263642378242, 7800.00, '222222222', 'f966a38e-0780-40be-bb52-5699d13cb3d9.jpg', '来自鲜嫩美味的小鸡，值得一尝', 1, 0, '2021-05-27 09:38:43', '2021-05-27 09:38:43', 1, 1, 0);
INSERT INTO take_out.dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397850140982161409, '毛氏红烧肉', 1397844263642378242, 6800.00, '123412341234', '0a3b3288-3446-4420-bbff-f263d0c02d8e.jpg', '毛氏红烧肉毛氏红烧肉，确定不来一份？', 1, 0, '2021-05-27 09:40:19', '2021-05-27 09:40:19', 1, 1, 0);
INSERT INTO take_out.dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397850392090947585, '组庵鱼翅', 1397844263642378242, 4800.00, '123412341234', '740c79ce-af29-41b8-b78d-5f49c96e38c4.jpg', '组庵鱼翅，看图足以表明好吃程度', 1, 0, '2021-05-27 09:41:19', '2021-05-27 09:41:19', 1, 1, 0);
INSERT INTO take_out.dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397850851245600769, '霸王别姬', 1397844263642378242, 12800.00, '123412341234', '057dd338-e487-4bbc-a74c-0384c44a9ca3.jpg', '还有什么比霸王别姬更美味的呢？', 1, 0, '2021-05-27 09:43:08', '2021-05-27 09:43:08', 1, 1, 0);
INSERT INTO take_out.dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397851099502260226, '全家福', 1397844263642378242, 11800.00, '23412341234', 'a53a4e6a-3b83-4044-87f9-9d49b30a8fdc.jpg', '别光吃肉啦，来份全家福吧，让你长寿又美味', 1, 0, '2021-05-27 09:44:08', '2021-05-27 09:44:08', 1, 1, 0);
INSERT INTO take_out.dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397851370462687234, '邵阳猪血丸子', 1397844263642378242, 13800.00, '1246812345678', '2a50628e-7758-4c51-9fbb-d37c61cdacad.jpg', '看，美味不？来嘛来嘛，这才是最爱吖', 1, 0, '2021-05-27 09:45:12', '2021-05-27 09:45:12', 1, 1, 0);
INSERT INTO take_out.dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397851668262465537, '口味蛇', 1397844263642378242, 16800.00, '1234567812345678', '0f4bd884-dc9c-4cf9-b59e-7d5958fec3dd.jpg', '爬行界的扛把子，东兴-口味蛇，让你欲罢不能', 1, 0, '2021-05-27 09:46:23', '2021-05-27 09:46:23', 1, 1, 0);
INSERT INTO take_out.dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397852391150759938, '辣子鸡丁', 1397844303408574465, 8800.00, '2346812468', 'ef2b73f2-75d1-4d3a-beea-22da0e1421bd.jpg', '辣子鸡丁，辣子鸡丁，永远的魂', 1, 0, '2021-05-27 09:49:16', '2021-05-27 09:49:16', 1, 1, 0);
INSERT INTO take_out.dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397853183287013378, '麻辣兔头', 1397844303408574465, 19800.00, '123456787654321', '2a2e9d66-b41d-4645-87bd-95f2cfeed218.jpg', '麻辣兔头的详细制作，麻辣鲜香，色泽红润，回味悠长', 1, 0, '2021-05-27 09:52:24', '2021-05-27 09:52:24', 1, 1, 0);
INSERT INTO take_out.dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397853709101740034, '蒜泥白肉', 1397844303408574465, 9800.00, '1234321234321', 'd2f61d70-ac85-4529-9b74-6d9a2255c6d7.jpg', '多么的有食欲啊', 1, 0, '2021-05-27 09:54:30', '2021-05-27 09:54:30', 1, 1, 0);
INSERT INTO take_out.dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397853890262118402, '鱼香肉丝', 1397844303408574465, 3800.00, '1234212321234', '8dcfda14-5712-4d28-82f7-ae905b3c2308.jpg', '鱼香肉丝简直就是我们童年回忆的一道经典菜，上学的时候点个鱼香肉丝盖饭坐在宿舍床上看着肥皂剧，绝了！现在完美复刻一下上学的时候感觉', 1, 0, '2021-05-27 09:55:13', '2021-05-27 09:55:13', 1, 1, 0);
INSERT INTO take_out.dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397854652581064706, '麻辣水煮鱼', 1397844303408574465, 14800.00, '2345312·345321', '1fdbfbf3-1d86-4b29-a3fc-46345852f2f8.jpg', '鱼片是买的切好的鱼片，放几个虾，增加味道', 1, 0, '2021-05-27 09:58:15', '2021-05-27 09:58:15', 1, 1, 0);
INSERT INTO take_out.dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397854865672679425, '鱼香炒鸡蛋', 1397844303408574465, 2000.00, '23456431·23456', '0f252364-a561-4e8d-8065-9a6797a6b1d3.jpg', '鱼香菜也是川味的特色。里面没有鱼却鱼香味', 1, 0, '2021-05-27 09:59:06', '2021-05-27 09:59:06', 1, 1, 0);
INSERT INTO take_out.dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397860242057375745, '脆皮烧鹅', 1397844391040167938, 12800.00, '123456786543213456', 'e476f679-5c15-436b-87fa-8c4e9644bf33.jpeg', '“广东烤鸭美而香，却胜烧鹅说古冈（今新会），燕瘦环肥各佳妙，君休偏重便宜坊”，可见烧鹅与烧鸭在粤菜之中已早负盛名。作为广州最普遍和最受欢迎的烧烤肉食，以它的“色泽金红，皮脆肉嫩，味香可口”的特色，在省城各大街小巷的烧卤店随处可见。', 1, 0, '2021-05-27 10:20:27', '2021-05-27 10:20:27', 1, 1, 0);
INSERT INTO take_out.dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397860578738352129, '白切鸡', 1397844391040167938, 6600.00, '12345678654', '9ec6fc2d-50d2-422e-b954-de87dcd04198.jpeg', '白切鸡是一道色香味俱全的特色传统名肴，又叫白斩鸡，是粤菜系鸡肴中的一种，始于清代的民间。白切鸡通常选用细骨农家鸡与沙姜、蒜茸等食材，慢火煮浸白切鸡皮爽肉滑，清淡鲜美。著名的泮溪酒家白切鸡，曾获商业部优质产品金鼎奖。湛江白切鸡更是驰名粤港澳。粤菜厨坛中，鸡的菜式有200余款之多，而最为人常食不厌的正是白切鸡，深受食家青睐。', 1, 0, '2021-05-27 10:21:48', '2021-05-27 10:21:48', 1, 1, 0);
INSERT INTO take_out.dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397860792492666881, '烤乳猪', 1397844391040167938, 38800.00, '213456432123456', '2e96a7e3-affb-438e-b7c3-e1430df425c9.jpeg', '广式烧乳猪主料是小乳猪，辅料是蒜，调料是五香粉、芝麻酱、八角粉等，本菜品主要通过将食材放入炭火中烧烤而成。烤乳猪是广州最著名的特色菜，并且是“满汉全席”中的主打菜肴之一。烤乳猪也是许多年来广东人祭祖的祭品之一，是家家都少不了的应节之物，用乳猪祭完先人后，亲戚们再聚餐食用。', 1, 0, '2021-05-27 10:22:39', '2021-05-27 10:22:39', 1, 1, 0);
INSERT INTO take_out.dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397860963880316929, '脆皮乳鸽', 1397844391040167938, 10800.00, '1234563212345', '3fabb83a-1c09-4fd9-892b-4ef7457daafa.jpeg', '“脆皮乳鸽”是广东菜中的一道传统名菜，属于粤菜系，具有皮脆肉嫩、色泽红亮、鲜香味美的特点，常吃可使身体强健，清肺顺气。随着菜品制作工艺的不断发展，逐渐形成了熟炸法、生炸法和烤制法三种制作方法。无论那种制作方法，都是在鸽子经过一系列的加工，挂脆皮水后再加工而成，正宗的“脆皮乳鸽皮脆肉嫩、色泽红亮、鲜香味美、香气馥郁。这三种方法的制作过程都不算复杂，但想达到理想的效果并不容易。', 1, 0, '2021-05-27 10:23:19', '2021-05-27 10:23:19', 1, 1, 0);
INSERT INTO take_out.dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397861683434139649, '清蒸河鲜海鲜', 1397844391040167938, 38800.00, '1234567876543213456', '1405081e-f545-42e1-86a2-f7559ae2e276.jpeg', '新鲜的海鲜，清蒸是最好的处理方式。鲜，体会为什么叫海鲜。清蒸是广州最经典的烹饪手法，过去岭南地区由于峻山大岭阻隔，交通不便，经济发展起步慢，自家打的鱼放在锅里煮了就吃，没有太多的讲究，但却发现这清淡的煮法能使鱼的鲜甜跃然舌尖。', 1, 0, '2021-05-27 10:26:11', '2021-05-27 10:26:11', 1, 1, 0);
INSERT INTO take_out.dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397862198033297410, '老火靓汤', 1397844391040167938, 49800.00, '123456786532455', '583df4b7-a159-4cfc-9543-4f666120b25f.jpeg', '老火靓汤又称广府汤，是广府人传承数千年的食补养生秘方，慢火煲煮的中华老火靓汤，火候足，时间长，既取药补之效，又取入口之甘甜。 广府老火汤种类繁多，可以用各种汤料和烹调方法，烹制出各种不同口味、不同功效的汤来。', 1, 0, '2021-05-27 10:28:14', '2021-05-27 10:28:14', 1, 1, 0);
INSERT INTO take_out.dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397862477831122945, '上汤焗龙虾', 1397844391040167938, 108800.00, '1234567865432', '5b8d2da3-3744-4bb3-acdc-329056b8259d.jpeg', '上汤焗龙虾是一道色香味俱全的传统名菜，属于粤菜系。此菜以龙虾为主料，配以高汤制成的一道海鲜美食。本品肉质洁白细嫩，味道鲜美，蛋白质含量高，脂肪含量低，营养丰富。是色香味俱全的传统名菜。', 1, 0, '2021-05-27 10:29:20', '2021-05-27 10:29:20', 1, 1, 0);
INSERT INTO take_out.dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1413342036832100354, '北冰洋', 1413341197421846529, 500.00, '', 'c99e0aab-3cb7-4eaa-80fd-f47d4ffea694.png', '', 1, 0, '2021-07-09 11:39:35', '2021-07-09 15:12:18', 1, 1, 0);
INSERT INTO take_out.dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1413384757047271425, '王老吉', 1413341197421846529, 500.00, '', '00874a5e-0df2-446b-8f69-a30eb7d88ee8.png', '怕上火就喝王老吉', 1, 0, '2021-07-09 14:29:20', '2022-06-17 13:53:20', 1, 1, 0);
INSERT INTO take_out.dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1413385247889891330, '米饭', 1413384954989060097, 200.00, '', 'ee04a05a-1230-46b6-8ad5-1a95b140fff3.png', '', 1, 0, '2021-07-09 14:31:17', '2021-07-11 16:35:26', 1, 1, 0);
INSERT INTO take_out.dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1537675947661066242, '黯然销魂饭', 1537622365431660546, 20000.00, '', '20220617135622620.jpg', '《食神》中周星驰饰演的史提芬周，落难时曾遭莫文蔚饰演的火鸡赠了一碗叉烧煎蛋饭，他在结局食神大赛中做出一碗叉烧煎蛋饭，就叫「黯然销魂饭」。', 1, 0, '2022-06-17 13:58:09', '2022-06-19 22:43:04', 1, 1, 0);

create table dish_flavor
(
    id          bigint        not null comment '主键'
        primary key,
    dish_id     bigint        not null comment '菜品',
    name        varchar(64)   not null comment '口味名称',
    value       varchar(500)  null comment '口味数据list',
    create_time datetime      not null comment '创建时间',
    update_time datetime      not null comment '更新时间',
    create_user bigint        not null comment '创建人',
    update_user bigint        not null comment '修改人',
    is_deleted  int default 0 not null comment '是否删除'
)
    comment '菜品口味关系表' collate = utf8_bin;

INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397849417888346113, 1397849417854791681, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-27 09:37:27', '2021-05-27 09:37:27', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397849739297861633, 1397849739276890114, '忌口', '["不要葱","不要蒜","不要香菜","不要辣"]', '2021-05-27 09:38:43', '2021-05-27 09:38:43', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397849739323027458, 1397849739276890114, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-27 09:38:43', '2021-05-27 09:38:43', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397849936421761025, 1397849936404983809, '忌口', '["不要葱","不要蒜","不要香菜","不要辣"]', '2021-05-27 09:39:30', '2021-05-27 09:39:30', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397849936438538241, 1397849936404983809, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-27 09:39:30', '2021-05-27 09:39:30', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397850141015715841, 1397850140982161409, '忌口', '["不要葱","不要蒜","不要香菜","不要辣"]', '2021-05-27 09:40:19', '2021-05-27 09:40:19', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397850141040881665, 1397850140982161409, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-27 09:40:19', '2021-05-27 09:40:19', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397850392120307713, 1397850392090947585, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-27 09:41:19', '2021-05-27 09:41:19', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397850392137084929, 1397850392090947585, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-27 09:41:19', '2021-05-27 09:41:19', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397850630734262274, 1397850630700707841, '忌口', '["不要葱","不要蒜","不要香菜","不要辣"]', '2021-05-27 09:42:16', '2021-05-27 09:42:16', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397850630755233794, 1397850630700707841, '辣度', '["微辣","中辣","重辣"]', '2021-05-27 09:42:16', '2021-05-27 09:42:16', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397850851274960898, 1397850851245600769, '忌口', '["不要蒜","不要香菜","不要辣"]', '2021-05-27 09:43:08', '2021-05-27 09:43:08', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397850851283349505, 1397850851245600769, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-27 09:43:08', '2021-05-27 09:43:08', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397851099523231745, 1397851099502260226, '忌口', '["不要葱","不要蒜","不要香菜","不要辣"]', '2021-05-27 09:44:08', '2021-05-27 09:44:08', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397851099527426050, 1397851099502260226, '辣度', '["不辣","微辣","中辣"]', '2021-05-27 09:44:08', '2021-05-27 09:44:08', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397851370483658754, 1397851370462687234, '温度', '["热饮","常温","去冰","少冰","多冰"]', '2021-05-27 09:45:12', '2021-05-27 09:45:12', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397851370483658755, 1397851370462687234, '忌口', '["不要葱","不要蒜","不要香菜","不要辣"]', '2021-05-27 09:45:12', '2021-05-27 09:45:12', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397851370483658756, 1397851370462687234, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-27 09:45:12', '2021-05-27 09:45:12', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397851668283437058, 1397851668262465537, '温度', '["热饮","常温","去冰","少冰","多冰"]', '2021-05-27 09:46:23', '2021-05-27 09:46:23', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397852391180120065, 1397852391150759938, '忌口', '["不要葱","不要香菜","不要辣"]', '2021-05-27 09:49:16', '2021-05-27 09:49:16', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397852391196897281, 1397852391150759938, '辣度', '["不辣","微辣","重辣"]', '2021-05-27 09:49:16', '2021-05-27 09:49:16', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397853183307984898, 1397853183287013378, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-27 09:52:24', '2021-05-27 09:52:24', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397853423486414850, 1397853423461249026, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-27 09:53:22', '2021-05-27 09:53:22', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397853709126905857, 1397853709101740034, '忌口', '["不要葱","不要蒜","不要香菜","不要辣"]', '2021-05-27 09:54:30', '2021-05-27 09:54:30', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397853890283089922, 1397853890262118402, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-27 09:55:13', '2021-05-27 09:55:13', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397854133632413697, 1397854133603053569, '温度', '["热饮","常温","去冰","少冰","多冰"]', '2021-05-27 09:56:11', '2021-05-27 09:56:11', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397854652623007745, 1397854652581064706, '忌口', '["不要葱","不要蒜","不要香菜","不要辣"]', '2021-05-27 09:58:15', '2021-05-27 09:58:15', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397854652635590658, 1397854652581064706, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-27 09:58:15', '2021-05-27 09:58:15', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397854865735593986, 1397854865672679425, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-27 09:59:06', '2021-05-27 09:59:06', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397855742303186946, 1397855742273826817, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-27 10:02:35', '2021-05-27 10:02:35', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397855906497605633, 1397855906468245506, '忌口', '["不要葱","不要蒜","不要香菜","不要辣"]', '2021-05-27 10:03:14', '2021-05-27 10:03:14', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397856190573621250, 1397856190540066818, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-27 10:04:21', '2021-05-27 10:04:21', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397859056709316609, 1397859056684150785, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-27 10:15:45', '2021-05-27 10:15:45', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397859277837217794, 1397859277812051969, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-27 10:16:37', '2021-05-27 10:16:37', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397859487502086146, 1397859487476920321, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-27 10:17:27', '2021-05-27 10:17:27', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397859757061615618, 1397859757036449794, '甜味', '["无糖","少糖","半躺","多糖","全糖"]', '2021-05-27 10:18:32', '2021-05-27 10:18:32', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397860242086735874, 1397860242057375745, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-27 10:20:27', '2021-05-27 10:20:27', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397860963918065665, 1397860963880316929, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-27 10:23:19', '2021-05-27 10:23:19', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397861135754506242, 1397861135733534722, '甜味', '["无糖","少糖","半躺","多糖","全糖"]', '2021-05-27 10:24:00', '2021-05-27 10:24:00', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397861370035744769, 1397861370010578945, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-27 10:24:56', '2021-05-27 10:24:56', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397861683459305474, 1397861683434139649, '忌口', '["不要葱","不要蒜","不要香菜","不要辣"]', '2021-05-27 10:26:11', '2021-05-27 10:26:11', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397861898467717121, 1397861898438356993, '忌口', '["不要葱","不要蒜","不要香菜","不要辣"]', '2021-05-27 10:27:02', '2021-05-27 10:27:02', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397862198054268929, 1397862198033297410, '忌口', '["不要葱","不要蒜","不要香菜","不要辣"]', '2021-05-27 10:28:14', '2021-05-27 10:28:14', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397862477835317250, 1397862477831122945, '辣度', '["不辣","微辣","中辣"]', '2021-05-27 10:29:20', '2021-05-27 10:29:20', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1398089545865015297, 1398089545676271617, '温度', '["热饮","常温","去冰","少冰","多冰"]', '2021-05-28 01:31:38', '2021-05-28 01:31:38', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1398089782323097601, 1398089782285348866, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-28 01:32:34', '2021-05-28 01:32:34', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1398090003262255106, 1398090003228700673, '忌口', '["不要葱","不要蒜","不要香菜","不要辣"]', '2021-05-28 01:33:27', '2021-05-28 01:33:27', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1398090264554811394, 1398090264517062657, '忌口', '["不要葱","不要蒜","不要香菜","不要辣"]', '2021-05-28 01:34:29', '2021-05-28 01:34:29', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1398090455399837698, 1398090455324340225, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-28 01:35:14', '2021-05-28 01:35:14', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1398090685449023490, 1398090685419663362, '温度', '["热饮","常温","去冰","少冰","多冰"]', '2021-05-28 01:36:09', '2021-05-28 01:36:09', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1398090825358422017, 1398090825329061889, '忌口', '["不要葱","不要蒜","不要香菜","不要辣"]', '2021-05-28 01:36:43', '2021-05-28 01:36:43', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1398091007051476993, 1398091007017922561, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-28 01:37:26', '2021-05-28 01:37:26', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1398091296164851713, 1398091296131297281, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-28 01:38:35', '2021-05-28 01:38:35', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1398091546531246081, 1398091546480914433, '忌口', '["不要葱","不要蒜","不要香菜","不要辣"]', '2021-05-28 01:39:35', '2021-05-28 01:39:35', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1398091729809747969, 1398091729788776450, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-28 01:40:18', '2021-05-28 01:40:18', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1398091889499484161, 1398091889449152513, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-28 01:40:56', '2021-05-28 01:40:56', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1398092095179763713, 1398092095142014978, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-28 01:41:45', '2021-05-28 01:41:45', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1398092283877306370, 1398092283847946241, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-28 01:42:30', '2021-05-28 01:42:30', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1398094018939236354, 1398094018893099009, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-28 01:49:24', '2021-05-28 01:49:24', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1398094391494094850, 1398094391456346113, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-28 01:50:53', '2021-05-28 01:50:53', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1399574026165727233, 1399305325713600514, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-06-01 03:50:25', '2021-06-01 03:50:25', 1399309715396669441, 1399309715396669441, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1413389684020682754, 1413342036832100354, '温度', '["常温","冷藏"]', '2021-07-09 15:12:18', '2021-07-09 15:12:18', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1537675947661066243, 1537675947661066242, '甜味', '["无糖","少糖"]', '2022-06-17 13:58:09', '2022-06-17 13:58:09', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1537675947661066244, 1537675947661066242, '辣度', '["不辣","微辣","中辣","重辣"]', '2022-06-17 13:58:09', '2022-06-17 13:58:09', 1, 1, 0);
INSERT INTO take_out.dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1537675947661066245, 1537675947661066242, '忌口', '["不要葱","不要蒜","不要香菜","不要辣"]', '2022-06-17 13:58:09', '2022-06-17 13:58:09', 1, 1, 0);

create table employee
(
    id          bigint auto_increment comment '主键'
        primary key,
    name        varchar(32)   not null comment '姓名',
    username    varchar(32)   not null comment '用户名',
    password    varchar(64)   not null comment '密码',
    phone       varchar(11)   not null comment '手机号',
    sex         varchar(2)    not null comment '性别',
    id_number   varchar(18)   not null comment '身份证号',
    status      int default 1 not null comment '状态 0:禁用，1:正常',
    create_time datetime      not null comment '创建时间',
    update_time datetime      not null comment '更新时间',
    create_user bigint        not null comment '创建人',
    update_user bigint        not null comment '修改人',
    constraint idx_username
        unique (username)
)
    comment '员工信息' collate = utf8_bin;

INSERT INTO take_out.employee (id, name, username, password, phone, sex, id_number, status, create_time, update_time, create_user, update_user) VALUES (1, '管理员', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '13812312312', '1', '110101199001010047', 1, '2021-05-06 17:20:07', '2021-05-10 02:24:09', 1, 1);
INSERT INTO take_out.employee (id, name, username, password, phone, sex, id_number, status, create_time, update_time, create_user, update_user) VALUES (2, '易敏', 'yimin', 'e10adc3949ba59abbe56e057f20f883e', '15553633208', '0', '620201197806171437', 1, '2022-06-16 22:03:05', '2022-06-16 22:03:05', 1, 1);
INSERT INTO take_out.employee (id, name, username, password, phone, sex, id_number, status, create_time, update_time, create_user, update_user) VALUES (3, '周静', 'zhoujing', 'e10adc3949ba59abbe56e057f20f883e', '18310196629', '0', '150101199307019845', 1, '2022-06-16 22:27:52', '2022-06-17 00:18:58', 1, 1);
INSERT INTO take_out.employee (id, name, username, password, phone, sex, id_number, status, create_time, update_time, create_user, update_user) VALUES (1537469331112222722, '杨勇', 'yangyong', 'e10adc3949ba59abbe56e057f20f883e', '17774796248', '1', '510201197509269010', 1, '2022-06-17 00:17:08', '2022-06-17 09:27:15', 1, 1);

create table order_detail
(
    id          bigint         not null comment '主键'
        primary key,
    name        varchar(50)    null comment '名字',
    image       varchar(100)   null comment '图片',
    order_id    bigint         not null comment '订单id',
    dish_id     bigint         null comment '菜品id',
    setmeal_id  bigint         null comment '套餐id',
    dish_flavor varchar(50)    null comment '口味',
    number      int default 1  not null comment '数量',
    amount      decimal(10, 2) not null comment '金额'
)
    comment '订单明细表' collate = utf8_bin;

INSERT INTO take_out.order_detail (id, name, image, order_id, dish_id, setmeal_id, dish_flavor, number, amount) VALUES (1538003848306655233, '口味蛇', '0f4bd884-dc9c-4cf9-b59e-7d5958fec3dd.jpg', 1538003848243740673, 1397851668262465537, null, '少冰', 1, 168.00);
INSERT INTO take_out.order_detail (id, name, image, order_id, dish_id, setmeal_id, dish_flavor, number, amount) VALUES (1538003848306655234, '全家福', 'a53a4e6a-3b83-4044-87f9-9d49b30a8fdc.jpg', 1538003848243740673, 1397851099502260226, null, '不要蒜,微辣', 1, 118.00);
INSERT INTO take_out.order_detail (id, name, image, order_id, dish_id, setmeal_id, dish_flavor, number, amount) VALUES (1538333632043679746, '麻辣水煮鱼', '1fdbfbf3-1d86-4b29-a3fc-46345852f2f8.jpg', 1538333632043679745, 1397854652581064706, null, '不要辣,不辣', 1, 148.00);
INSERT INTO take_out.order_detail (id, name, image, order_id, dish_id, setmeal_id, dish_flavor, number, amount) VALUES (1538365788426129410, '鱼香炒鸡蛋', '0f252364-a561-4e8d-8065-9a6797a6b1d3.jpg', 1538365788426129409, 1397854865672679425, null, '中辣', 1, 20.00);
INSERT INTO take_out.order_detail (id, name, image, order_id, dish_id, setmeal_id, dish_flavor, number, amount) VALUES (1538365788426129411, '王老吉', '00874a5e-0df2-446b-8f69-a30eb7d88ee8.png', 1538365788426129409, 1413384757047271425, null, null, 1, 5.00);
INSERT INTO take_out.order_detail (id, name, image, order_id, dish_id, setmeal_id, dish_flavor, number, amount) VALUES (1538365788426129412, '米饭', 'ee04a05a-1230-46b6-8ad5-1a95b140fff3.png', 1538365788426129409, 1413385247889891330, null, null, 1, 2.00);
INSERT INTO take_out.order_detail (id, name, image, order_id, dish_id, setmeal_id, dish_flavor, number, amount) VALUES (1538365788426129413, '白切鸡', '9ec6fc2d-50d2-422e-b954-de87dcd04198.jpeg', 1538365788426129409, 1397860578738352129, null, null, 1, 66.00);

create table orders
(
    id              bigint         not null comment '主键'
        primary key,
    number          varchar(50)    null comment '订单号',
    status          int default 1  not null comment '订单状态 1待付款，2待派送，3已派送，4已完成，5已取消',
    user_id         bigint         not null comment '下单用户',
    address_book_id bigint         not null comment '地址id',
    order_time      datetime       not null comment '下单时间',
    checkout_time   datetime       not null comment '结账时间',
    pay_method      int default 1  not null comment '支付方式 1微信,2支付宝',
    amount          decimal(10, 2) not null comment '实收金额',
    remark          varchar(100)   null comment '备注',
    phone           varchar(255)   null,
    address         varchar(255)   null,
    user_name       varchar(255)   null,
    consignee       varchar(255)   null
)
    comment '订单表' collate = utf8_bin;

INSERT INTO take_out.orders (id, number, status, user_id, address_book_id, order_time, checkout_time, pay_method, amount, remark, phone, address, user_name, consignee) VALUES (1538003848243740673, '1538003848243740673', 3, 1538003320973590529, 1538003695206170626, '2022-06-18 11:41:07', '2022-06-18 11:41:07', 1, 286.00, '加快速度', '14710140960', '重庆市市辖区开州区正安街道', null, '龙强');
INSERT INTO take_out.orders (id, number, status, user_id, address_book_id, order_time, checkout_time, pay_method, amount, remark, phone, address, user_name, consignee) VALUES (1538333632043679745, '1538333632043679745', 2, 1538003320973590529, 1538003695206170626, '2022-06-19 09:31:33', '2022-06-19 09:31:33', 1, 148.00, '不要辣', '14710140960', '重庆市市辖区开州区正安街道', null, '龙强');
INSERT INTO take_out.orders (id, number, status, user_id, address_book_id, order_time, checkout_time, pay_method, amount, remark, phone, address, user_name, consignee) VALUES (1538365788426129409, '1538365788426129409', 4, 1538003320973590529, 1538003695206170626, '2022-06-19 11:39:20', '2022-06-19 11:39:20', 1, 93.00, '饿了', '14710140960', '重庆市市辖区开州区正安街道', null, '龙强');

create table set_meal
(
    id          bigint         not null comment '主键'
        primary key,
    category_id bigint         not null comment '菜品分类id',
    name        varchar(64)    not null comment '套餐名称',
    price       decimal(10, 2) not null comment '套餐价格',
    status      int            null comment '状态 0:停用 1:启用',
    code        varchar(32)    null comment '编码',
    description varchar(512)   null comment '描述信息',
    image       varchar(255)   null comment '图片',
    create_time datetime       not null comment '创建时间',
    update_time datetime       not null comment '更新时间',
    create_user bigint         not null comment '创建人',
    update_user bigint         not null comment '修改人',
    is_deleted  int default 0  not null comment '是否删除',
    constraint idx_setmeal_name
        unique (name)
)
    comment '套餐' collate = utf8_bin;

INSERT INTO take_out.set_meal (id, category_id, name, price, status, code, description, image, create_time, update_time, create_user, update_user, is_deleted) VALUES (1415580119015145474, 1413386191767674881, '儿童套餐A计划', 4000.00, 1, '', '', '61d20592-b37f-4d72-a864-07ad5bb8f3bb.jpg', '2021-07-15 15:52:55', '2021-07-15 15:52:55', 1415576781934608386, 1415576781934608386, 0);
INSERT INTO take_out.set_meal (id, category_id, name, price, status, code, description, image, create_time, update_time, create_user, update_user, is_deleted) VALUES (1537715675819175937, 1413342269393674242, '豪华超值套餐A', 28800.00, 1, '', '', '20220617163559543.jpg', '2022-06-17 16:36:01', '2022-06-19 22:42:56', 1, 1, 0);

create table set_meal_dish
(
    id          bigint         not null comment '主键'
        primary key,
    setmeal_id  varchar(32)    not null comment '套餐id ',
    dish_id     varchar(32)    not null comment '菜品id',
    name        varchar(32)    null comment '菜品名称 （冗余字段）',
    price       decimal(10, 2) null comment '菜品原价（冗余字段）',
    copies      int            not null comment '份数',
    sort        int default 0  not null comment '排序',
    create_time datetime       not null comment '创建时间',
    update_time datetime       not null comment '更新时间',
    create_user bigint         not null comment '创建人',
    update_user bigint         not null comment '修改人',
    is_deleted  int default 0  not null comment '是否删除'
)
    comment '套餐菜品关系' collate = utf8_bin;

INSERT INTO take_out.set_meal_dish (id, setmeal_id, dish_id, name, price, copies, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1415580119052894209, '1415580119015145474', '1397862198033297410', '老火靓汤', 49800.00, 1, 0, '2021-07-15 15:52:55', '2021-07-15 15:52:55', 1415576781934608386, 1415576781934608386, 0);
INSERT INTO take_out.set_meal_dish (id, setmeal_id, dish_id, name, price, copies, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1415580119061282817, '1415580119015145474', '1413342036832100354', '北冰洋', 500.00, 1, 0, '2021-07-15 15:52:55', '2021-07-15 15:52:55', 1415576781934608386, 1415576781934608386, 0);
INSERT INTO take_out.set_meal_dish (id, setmeal_id, dish_id, name, price, copies, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1415580119069671426, '1415580119015145474', '1413385247889891330', '米饭', 200.00, 1, 0, '2021-07-15 15:52:55', '2021-07-15 15:52:55', 1415576781934608386, 1415576781934608386, 0);
INSERT INTO take_out.set_meal_dish (id, setmeal_id, dish_id, name, price, copies, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1537715675819175938, '1537715675819175937', '1397860578738352129', '白切鸡', 6600.00, 1, 0, '2022-06-17 16:36:01', '2022-06-17 16:36:01', 1, 1, 0);
INSERT INTO take_out.set_meal_dish (id, setmeal_id, dish_id, name, price, copies, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1537715675819175939, '1537715675819175937', '1397860242057375745', '脆皮烧鹅', 12800.00, 1, 0, '2022-06-17 16:36:01', '2022-06-17 16:36:01', 1, 1, 0);
INSERT INTO take_out.set_meal_dish (id, setmeal_id, dish_id, name, price, copies, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1537730089523924994, '1537715675819175937', '1413342036832100354', '北冰洋', 500.00, 1, 0, '2022-06-17 17:33:18', '2022-06-17 17:33:18', 1, 1, 0);
INSERT INTO take_out.set_meal_dish (id, setmeal_id, dish_id, name, price, copies, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1537730154145566721, '1537715675819175937', '1413385247889891330', '米饭', 200.00, 1, 0, '2022-06-17 17:33:33', '2022-06-17 17:33:33', 1, 1, 0);

create table shopping_cart
(
    id          bigint         not null comment '主键'
        primary key,
    name        varchar(50)    null comment '名称',
    image       varchar(100)   null comment '图片',
    user_id     bigint         not null comment '用户id',
    dish_id     bigint         null comment '菜品id',
    setmeal_id  bigint         null comment '套餐id',
    dish_flavor varchar(50)    null comment '口味',
    number      int default 1  not null comment '数量',
    amount      decimal(10, 2) not null comment '金额',
    create_time datetime       null comment '创建时间'
)
    comment '购物车' collate = utf8_bin;

INSERT INTO take_out.shopping_cart (id, name, image, user_id, dish_id, setmeal_id, dish_flavor, number, amount, create_time) VALUES (1537999807950376961, '米饭', 'ee04a05a-1230-46b6-8ad5-1a95b140fff3.png', 1537999718397792258, 1413385247889891330, null, null, 1, 2.00, '2022-06-18 11:25:04');
INSERT INTO take_out.shopping_cart (id, name, image, user_id, dish_id, setmeal_id, dish_flavor, number, amount, create_time) VALUES (1537999827671994370, '上汤焗龙虾', '5b8d2da3-3744-4bb3-acdc-329056b8259d.jpeg', 1537999718397792258, 1397862477831122945, null, '中辣', 1, 1088.00, '2022-06-18 11:25:08');
INSERT INTO take_out.shopping_cart (id, name, image, user_id, dish_id, setmeal_id, dish_flavor, number, amount, create_time) VALUES (1538463206001881091, '米饭', 'ee04a05a-1230-46b6-8ad5-1a95b140fff3.png', 1538003320973590529, 1413385247889891330, null, null, 1, 2.00, null);
INSERT INTO take_out.shopping_cart (id, name, image, user_id, dish_id, setmeal_id, dish_flavor, number, amount, create_time) VALUES (1538463206001881092, '白切鸡', '9ec6fc2d-50d2-422e-b954-de87dcd04198.jpeg', 1538003320973590529, 1397860578738352129, null, null, 1, 66.00, null);

create table user
(
    id        bigint        not null comment '主键'
        primary key,
    name      varchar(50)   null comment '姓名',
    phone     varchar(100)  not null comment '手机号',
    sex       varchar(2)    null comment '性别',
    id_number varchar(18)   null comment '身份证号',
    avatar    varchar(500)  null comment '头像',
    status    int default 0 null comment '状态 0:禁用，1:正常'
)
    comment '用户信息' collate = utf8_bin;

INSERT INTO take_out.user (id, name, phone, sex, id_number, avatar, status) VALUES (1537999718397792258, null, '17533187672', null, null, null, 1);
INSERT INTO take_out.user (id, name, phone, sex, id_number, avatar, status) VALUES (1538003320973590529, null, '18096190334', null, null, null, 1);