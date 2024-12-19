drop schema if exists my_take_out;
create schema my_take_out;

use my_take_out;

create table address_book
(
    id            bigint       null,
    user_id       bigint       null,
    consignee     varchar(50)  null,
    sex           tinyint      null,
    phone         varchar(11)  null,
    province_code varchar(12)  null,
    province_name varchar(32)  null,
    city_code     varchar(12)  null,
    city_name     varchar(32)  null,
    district_code varchar(12)  null,
    district_name varchar(32)  null,
    detail        varchar(200) null,
    label         varchar(100) null,
    is_default    tinyint      null,
    create_time   datetime     null,
    update_time   datetime     null,
    create_user   bigint       null,
    update_user   bigint       null,
    is_deleted    int          null
);

INSERT INTO address_book (id, user_id, consignee, sex, phone, province_code, province_name, city_code, city_name, district_code, district_name, detail, label, is_default, create_time, update_time, create_user, update_user, is_deleted) VALUES (1747173138608848898, 1738402096889384961, '冯', 1, '15903551284', null, null, null, null, null, null, '无', '公司', 1, '2024-01-16 16:25:12', '2024-01-16 16:25:13', 1738402096889384961, 1738402096889384961, null);


create table category
(
    id          bigint      null,
    type        int         null,
    name        varchar(64) null,
    sort        int         null,
    create_time datetime    null,
    update_time datetime    null,
    create_user bigint      null,
    update_user bigint      null
);

INSERT INTO category (id, type, name, sort, create_time, update_time, create_user, update_user) VALUES (1397844263642378242, 1, '湘菜', 1, '2021-05-27 09:16:58', '2021-07-15 20:25:23', 1, 1);
INSERT INTO category (id, type, name, sort, create_time, update_time, create_user, update_user) VALUES (1397844303408574465, 1, '川菜', 2, '2021-05-27 09:17:07', '2021-06-02 14:27:22', 1, 1);
INSERT INTO category (id, type, name, sort, create_time, update_time, create_user, update_user) VALUES (1397844391040167938, 1, '粤菜', 3, '2021-05-27 09:17:28', '2021-07-09 14:37:13', 1, 1);
INSERT INTO category (id, type, name, sort, create_time, update_time, create_user, update_user) VALUES (1413341197421846529, 1, '饮品', 11, '2021-07-09 11:36:15', '2021-07-09 14:39:15', 1, 1);
INSERT INTO category (id, type, name, sort, create_time, update_time, create_user, update_user) VALUES (1413384954989060097, 1, '主食', 12, '2021-07-09 14:30:07', '2021-07-09 14:39:19', 1, 1);
INSERT INTO category (id, type, name, sort, create_time, update_time, create_user, update_user) VALUES (1413386191767674881, 2, '儿童套餐', 6, '2021-07-09 14:35:02', '2021-07-09 14:39:05', 1, 1);



create table dish
(
    id          bigint         null,
    name        varchar(64)    null,
    category_id bigint         null,
    price       decimal(10, 2) null,
    code        varchar(64)    null,
    image       varchar(200)   null,
    description varchar(400)   null,
    status      int            null,
    sort        int            null,
    create_time datetime       null,
    update_time datetime       null,
    create_user bigint         null,
    update_user bigint         null,
    is_deleted  int            null
);

INSERT INTO dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397849739276890114, '辣子鸡', 1397844263642378242, 7800.00, '222222222', 'f966a38e-0780-40be-bb52-5699d13cb3d9.jpg', '来自鲜嫩美味的小鸡，值得一尝', 1, 0, '2021-05-27 09:38:43', '2021-05-27 09:38:43', 1, 1, 0);
INSERT INTO dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397850140982161409, '毛氏红烧肉', 1397844263642378242, 6800.00, '123412341234', '0a3b3288-3446-4420-bbff-f263d0c02d8e.jpg', '毛氏红烧肉毛氏红烧肉，确定不来一份？', 1, 0, '2021-05-27 09:40:19', '2021-05-27 09:40:19', 1, 1, 0);
INSERT INTO dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397850392090947585, '组庵鱼翅', 1397844263642378242, 4800.00, '123412341234', '740c79ce-af29-41b8-b78d-5f49c96e38c4.jpg', '组庵鱼翅，看图足以表明好吃程度', 1, 0, '2021-05-27 09:41:19', '2021-05-27 09:41:19', 1, 1, 0);
INSERT INTO dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397850851245600769, '霸王别姬', 1397844263642378242, 12800.00, '123412341234', '057dd338-e487-4bbc-a74c-0384c44a9ca3.jpg', '还有什么比霸王别姬更美味的呢？', 1, 0, '2021-05-27 09:43:08', '2021-05-27 09:43:08', 1, 1, 0);
INSERT INTO dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397851099502260226, '全家福', 1397844263642378242, 11800.00, '23412341234', 'a53a4e6a-3b83-4044-87f9-9d49b30a8fdc.jpg', '别光吃肉啦，来份全家福吧，让你长寿又美味', 1, 0, '2021-05-27 09:44:08', '2021-05-27 09:44:08', 1, 1, 0);
INSERT INTO dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397851370462687234, '邵阳猪血丸子', 1397844263642378242, 13800.00, '1246812345678', '2a50628e-7758-4c51-9fbb-d37c61cdacad.jpg', '看，美味不？来嘛来嘛，这才是最爱吖', 1, 0, '2021-05-27 09:45:12', '2021-05-27 09:45:12', 1, 1, 0);
INSERT INTO dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397851668262465537, '口味蛇', 1397844263642378242, 16800.00, '1234567812345678', '0f4bd884-dc9c-4cf9-b59e-7d5958fec3dd.jpg', '爬行界的扛把子，东兴-口味蛇，让你欲罢不能', 1, 0, '2021-05-27 09:46:23', '2021-05-27 09:46:23', 1, 1, 0);
INSERT INTO dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397852391150759938, '辣子鸡丁', 1397844303408574465, 8800.00, '2346812468', 'ef2b73f2-75d1-4d3a-beea-22da0e1421bd.jpg', '辣子鸡丁，辣子鸡丁，永远的魂', 1, 0, '2021-05-27 09:49:16', '2021-05-27 09:49:16', 1, 1, 0);
INSERT INTO dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397853183287013378, '麻辣兔头', 1397844303408574465, 19800.00, '123456787654321', '2a2e9d66-b41d-4645-87bd-95f2cfeed218.jpg', '麻辣兔头的详细制作，麻辣鲜香，色泽红润，回味悠长', 1, 0, '2021-05-27 09:52:24', '2021-05-27 09:52:24', 1, 1, 0);
INSERT INTO dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397853709101740034, '蒜泥白肉', 1397844303408574465, 9800.00, '1234321234321', 'd2f61d70-ac85-4529-9b74-6d9a2255c6d7.jpg', '多么的有食欲啊', 1, 0, '2021-05-27 09:54:30', '2021-05-27 09:54:30', 1, 1, 0);
INSERT INTO dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397853890262118402, '鱼香肉丝', 1397844303408574465, 3800.00, '1234212321234', '8dcfda14-5712-4d28-82f7-ae905b3c2308.jpg', '鱼香肉丝简直就是我们童年回忆的一道经典菜，上学的时候点个鱼香肉丝盖饭坐在宿舍床上看着肥皂剧，绝了！现在完美复刻一下上学的时候感觉', 1, 0, '2021-05-27 09:55:13', '2021-05-27 09:55:13', 1, 1, 0);
INSERT INTO dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397854652581064706, '麻辣水煮鱼', 1397844303408574465, 13800.00, '2345312·345321', '1fdbfbf3-1d86-4b29-a3fc-46345852f2f8.jpg', '鱼片是买的切好的鱼片，放几个虾，增加味道', 1, 0, '2021-05-27 09:58:15', '2024-01-12 16:28:37', 1, 1, 0);
INSERT INTO dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397854865672679425, '鱼香炒鸡蛋', 1397844303408574465, 2000.00, '23456431·23456', '0f252364-a561-4e8d-8065-9a6797a6b1d3.jpg', '鱼香菜也是川味的特色。里面没有鱼却鱼香味', 1, 0, '2021-05-27 09:59:06', '2021-05-27 09:59:06', 1, 1, 0);
INSERT INTO dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397860242057375745, '脆皮烧鹅', 1397844391040167938, 12800.00, '123456786543213456', 'e476f679-5c15-436b-87fa-8c4e9644bf33.jpeg', '“广东烤鸭美而香，却胜烧鹅说古冈（今新会），燕瘦环肥各佳妙，君休偏重便宜坊”，可见烧鹅与烧鸭在粤菜之中已早负盛名。作为广州最普遍和最受欢迎的烧烤肉食，以它的“色泽金红，皮脆肉嫩，味香可口”的特色，在省城各大街小巷的烧卤店随处可见。', 1, 0, '2021-05-27 10:20:27', '2021-05-27 10:20:27', 1, 1, 0);
INSERT INTO dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397860578738352129, '白切鸡', 1397844391040167938, 6600.00, '12345678654', '9ec6fc2d-50d2-422e-b954-de87dcd04198.jpeg', '白切鸡是一道色香味俱全的特色传统名肴，又叫白斩鸡，是粤菜系鸡肴中的一种，始于清代的民间。白切鸡通常选用细骨农家鸡与沙姜、蒜茸等食材，慢火煮浸白切鸡皮爽肉滑，清淡鲜美。著名的泮溪酒家白切鸡，曾获商业部优质产品金鼎奖。湛江白切鸡更是驰名粤港澳。粤菜厨坛中，鸡的菜式有200余款之多，而最为人常食不厌的正是白切鸡，深受食家青睐。', 1, 0, '2021-05-27 10:21:48', '2021-05-27 10:21:48', 1, 1, 0);
INSERT INTO dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397860792492666881, '烤乳猪', 1397844391040167938, 38800.00, '213456432123456', '2e96a7e3-affb-438e-b7c3-e1430df425c9.jpeg', '广式烧乳猪主料是小乳猪，辅料是蒜，调料是五香粉、芝麻酱、八角粉等，本菜品主要通过将食材放入炭火中烧烤而成。烤乳猪是广州最著名的特色菜，并且是“满汉全席”中的主打菜肴之一。烤乳猪也是许多年来广东人祭祖的祭品之一，是家家都少不了的应节之物，用乳猪祭完先人后，亲戚们再聚餐食用。', 1, 0, '2021-05-27 10:22:39', '2021-05-27 10:22:39', 1, 1, 0);
INSERT INTO dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397860963880316929, '脆皮乳鸽', 1397844391040167938, 10800.00, '1234563212345', '3fabb83a-1c09-4fd9-892b-4ef7457daafa.jpeg', '“脆皮乳鸽”是广东菜中的一道传统名菜，属于粤菜系，具有皮脆肉嫩、色泽红亮、鲜香味美的特点，常吃可使身体强健，清肺顺气。随着菜品制作工艺的不断发展，逐渐形成了熟炸法、生炸法和烤制法三种制作方法。无论那种制作方法，都是在鸽子经过一系列的加工，挂脆皮水后再加工而成，正宗的“脆皮乳鸽皮脆肉嫩、色泽红亮、鲜香味美、香气馥郁。这三种方法的制作过程都不算复杂，但想达到理想的效果并不容易。', 1, 0, '2021-05-27 10:23:19', '2021-05-27 10:23:19', 1, 1, 0);
INSERT INTO dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397861683434139649, '清蒸河鲜海鲜', 1397844391040167938, 38800.00, '1234567876543213456', '1405081e-f545-42e1-86a2-f7559ae2e276.jpeg', '新鲜的海鲜，清蒸是最好的处理方式。鲜，体会为什么叫海鲜。清蒸是广州最经典的烹饪手法，过去岭南地区由于峻山大岭阻隔，交通不便，经济发展起步慢，自家打的鱼放在锅里煮了就吃，没有太多的讲究，但却发现这清淡的煮法能使鱼的鲜甜跃然舌尖。', 1, 0, '2021-05-27 10:26:11', '2021-05-27 10:26:11', 1, 1, 0);
INSERT INTO dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397862198033297410, '老火靓汤', 1397844391040167938, 49800.00, '123456786532455', '583df4b7-a159-4cfc-9543-4f666120b25f.jpeg', '老火靓汤又称广府汤，是广府人传承数千年的食补养生秘方，慢火煲煮的中华老火靓汤，火候足，时间长，既取药补之效，又取入口之甘甜。 广府老火汤种类繁多，可以用各种汤料和烹调方法，烹制出各种不同口味、不同功效的汤来。', 1, 0, '2021-05-27 10:28:14', '2021-05-27 10:28:14', 1, 1, 0);
INSERT INTO dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397862477831122945, '上汤焗龙虾', 1397844391040167938, 108800.00, '1234567865432', '5b8d2da3-3744-4bb3-acdc-329056b8259d.jpeg', '上汤焗龙虾是一道色香味俱全的传统名菜，属于粤菜系。此菜以龙虾为主料，配以高汤制成的一道海鲜美食。本品肉质洁白细嫩，味道鲜美，蛋白质含量高，脂肪含量低，营养丰富。是色香味俱全的传统名菜。', 1, 0, '2021-05-27 10:29:20', '2021-05-27 10:29:20', 1, 1, 0);
INSERT INTO dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1413342036832100354, '北冰洋', 1413341197421846529, 500.00, '', 'c99e0aab-3cb7-4eaa-80fd-f47d4ffea694.png', '', 1, 0, '2021-07-09 11:39:35', '2021-07-09 15:12:18', 1, 1, 0);
INSERT INTO dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1413384757047271425, '王老吉', 1413341197421846529, 500.00, '', '00874a5e-0df2-446b-8f69-a30eb7d88ee8.png', '', 1, 0, '2021-07-09 14:29:20', '2021-07-12 09:09:16', 1, 1, 0);
INSERT INTO dish (id, name, category_id, price, code, image, description, status, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1413385247889891330, '米饭', 1413384954989060097, 200.00, '', 'ee04a05a-1230-46b6-8ad5-1a95b140fff3.png', '', 1, 0, '2021-07-09 14:31:17', '2021-07-11 16:35:26', 1, 1, 0);


create table dish_flavor
(
    id          bigint       null,
    dish_id     bigint       null,
    name        varchar(64)  null,
    value       varchar(500) null,
    create_time datetime     null,
    update_time datetime     null,
    create_user bigint       null,
    update_user bigint       null,
    is_deleted  int          null
);

INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397849417888346113, 1397849417854791681, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-27 09:37:27', '2021-05-27 09:37:27', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397849739297861633, 1397849739276890114, '忌口', '["不要葱","不要蒜","不要香菜","不要辣"]', '2021-05-27 09:38:43', '2021-05-27 09:38:43', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397849739323027458, 1397849739276890114, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-27 09:38:43', '2021-05-27 09:38:43', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397849936421761025, 1397849936404983809, '忌口', '["不要葱","不要蒜","不要香菜","不要辣"]', '2021-05-27 09:39:30', '2021-05-27 09:39:30', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397849936438538241, 1397849936404983809, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-27 09:39:30', '2021-05-27 09:39:30', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397850141015715841, 1397850140982161409, '忌口', '["不要葱","不要蒜","不要香菜","不要辣"]', '2021-05-27 09:40:19', '2021-05-27 09:40:19', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397850141040881665, 1397850140982161409, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-27 09:40:19', '2021-05-27 09:40:19', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397850392120307713, 1397850392090947585, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-27 09:41:19', '2021-05-27 09:41:19', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397850392137084929, 1397850392090947585, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-27 09:41:19', '2021-05-27 09:41:19', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397850630734262274, 1397850630700707841, '忌口', '["不要葱","不要蒜","不要香菜","不要辣"]', '2021-05-27 09:42:16', '2021-05-27 09:42:16', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397850630755233794, 1397850630700707841, '辣度', '["微辣","中辣","重辣"]', '2021-05-27 09:42:16', '2021-05-27 09:42:16', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397850851274960898, 1397850851245600769, '忌口', '["不要蒜","不要香菜","不要辣"]', '2021-05-27 09:43:08', '2021-05-27 09:43:08', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397850851283349505, 1397850851245600769, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-27 09:43:08', '2021-05-27 09:43:08', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397851099523231745, 1397851099502260226, '忌口', '["不要葱","不要蒜","不要香菜","不要辣"]', '2021-05-27 09:44:08', '2021-05-27 09:44:08', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397851099527426050, 1397851099502260226, '辣度', '["不辣","微辣","中辣"]', '2021-05-27 09:44:08', '2021-05-27 09:44:08', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397851370483658754, 1397851370462687234, '温度', '["热饮","常温","去冰","少冰","多冰"]', '2021-05-27 09:45:12', '2021-05-27 09:45:12', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397851370483658755, 1397851370462687234, '忌口', '["不要葱","不要蒜","不要香菜","不要辣"]', '2021-05-27 09:45:12', '2021-05-27 09:45:12', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397851370483658756, 1397851370462687234, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-27 09:45:12', '2021-05-27 09:45:12', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397851668283437058, 1397851668262465537, '温度', '["热饮","常温","去冰","少冰","多冰"]', '2021-05-27 09:46:23', '2021-05-27 09:46:23', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397852391180120065, 1397852391150759938, '忌口', '["不要葱","不要香菜","不要辣"]', '2021-05-27 09:49:16', '2021-05-27 09:49:16', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397852391196897281, 1397852391150759938, '辣度', '["不辣","微辣","重辣"]', '2021-05-27 09:49:16', '2021-05-27 09:49:16', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397853183307984898, 1397853183287013378, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-27 09:52:24', '2021-05-27 09:52:24', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397853423486414850, 1397853423461249026, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-27 09:53:22', '2021-05-27 09:53:22', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397853709126905857, 1397853709101740034, '忌口', '["不要葱","不要蒜","不要香菜","不要辣"]', '2021-05-27 09:54:30', '2021-05-27 09:54:30', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397853890283089922, 1397853890262118402, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-27 09:55:13', '2021-05-27 09:55:13', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397854133632413697, 1397854133603053569, '温度', '["热饮","常温","去冰","少冰","多冰"]', '2021-05-27 09:56:11', '2021-05-27 09:56:11', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397854652623007745, 1397854652581064706, '忌口', '["不要葱","不要蒜","不要香菜","不要辣"]', '2024-01-12 16:28:37', '2024-01-12 16:28:37', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397854652635590658, 1397854652581064706, '辣度', '["不辣","微辣","中辣","重辣"]', '2024-01-12 16:28:37', '2024-01-12 16:28:37', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397854865735593986, 1397854865672679425, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-27 09:59:06', '2021-05-27 09:59:06', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397855742303186946, 1397855742273826817, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-27 10:02:35', '2021-05-27 10:02:35', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397855906497605633, 1397855906468245506, '忌口', '["不要葱","不要蒜","不要香菜","不要辣"]', '2021-05-27 10:03:14', '2021-05-27 10:03:14', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397856190573621250, 1397856190540066818, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-27 10:04:21', '2021-05-27 10:04:21', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397859056709316609, 1397859056684150785, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-27 10:15:45', '2021-05-27 10:15:45', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397859277837217794, 1397859277812051969, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-27 10:16:37', '2021-05-27 10:16:37', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397859487502086146, 1397859487476920321, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-27 10:17:27', '2021-05-27 10:17:27', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397859757061615618, 1397859757036449794, '甜味', '["无糖","少糖","半躺","多糖","全糖"]', '2021-05-27 10:18:32', '2021-05-27 10:18:32', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397860242086735874, 1397860242057375745, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-27 10:20:27', '2021-05-27 10:20:27', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397860963918065665, 1397860963880316929, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-27 10:23:19', '2021-05-27 10:23:19', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397861135754506242, 1397861135733534722, '甜味', '["无糖","少糖","半躺","多糖","全糖"]', '2021-05-27 10:24:00', '2021-05-27 10:24:00', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397861370035744769, 1397861370010578945, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-27 10:24:56', '2021-05-27 10:24:56', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397861683459305474, 1397861683434139649, '忌口', '["不要葱","不要蒜","不要香菜","不要辣"]', '2021-05-27 10:26:11', '2021-05-27 10:26:11', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397861898467717121, 1397861898438356993, '忌口', '["不要葱","不要蒜","不要香菜","不要辣"]', '2021-05-27 10:27:02', '2021-05-27 10:27:02', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397862198054268929, 1397862198033297410, '忌口', '["不要葱","不要蒜","不要香菜","不要辣"]', '2021-05-27 10:28:14', '2021-05-27 10:28:14', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1397862477835317250, 1397862477831122945, '辣度', '["不辣","微辣","中辣"]', '2021-05-27 10:29:20', '2021-05-27 10:29:20', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1398089545865015297, 1398089545676271617, '温度', '["热饮","常温","去冰","少冰","多冰"]', '2021-05-28 01:31:38', '2021-05-28 01:31:38', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1398089782323097601, 1398089782285348866, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-28 01:32:34', '2021-05-28 01:32:34', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1398090003262255106, 1398090003228700673, '忌口', '["不要葱","不要蒜","不要香菜","不要辣"]', '2021-05-28 01:33:27', '2021-05-28 01:33:27', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1398090264554811394, 1398090264517062657, '忌口', '["不要葱","不要蒜","不要香菜","不要辣"]', '2021-05-28 01:34:29', '2021-05-28 01:34:29', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1398090455399837698, 1398090455324340225, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-28 01:35:14', '2021-05-28 01:35:14', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1398090685449023490, 1398090685419663362, '温度', '["热饮","常温","去冰","少冰","多冰"]', '2021-05-28 01:36:09', '2021-05-28 01:36:09', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1398090825358422017, 1398090825329061889, '忌口', '["不要葱","不要蒜","不要香菜","不要辣"]', '2021-05-28 01:36:43', '2021-05-28 01:36:43', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1398091007051476993, 1398091007017922561, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-28 01:37:26', '2021-05-28 01:37:26', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1398091296164851713, 1398091296131297281, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-28 01:38:35', '2021-05-28 01:38:35', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1398091546531246081, 1398091546480914433, '忌口', '["不要葱","不要蒜","不要香菜","不要辣"]', '2021-05-28 01:39:35', '2021-05-28 01:39:35', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1398091729809747969, 1398091729788776450, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-28 01:40:18', '2021-05-28 01:40:18', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1398091889499484161, 1398091889449152513, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-28 01:40:56', '2021-05-28 01:40:56', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1398092095179763713, 1398092095142014978, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-28 01:41:45', '2021-05-28 01:41:45', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1398092283877306370, 1398092283847946241, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-28 01:42:30', '2021-05-28 01:42:30', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1398094018939236354, 1398094018893099009, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-28 01:49:24', '2021-05-28 01:49:24', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1398094391494094850, 1398094391456346113, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-05-28 01:50:53', '2021-05-28 01:50:53', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1399574026165727233, 1399305325713600514, '辣度', '["不辣","微辣","中辣","重辣"]', '2021-06-01 03:50:25', '2021-06-01 03:50:25', 1399309715396669441, 1399309715396669441, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1413389540592263169, 1413384757047271425, '温度', '["常温","冷藏"]', '2021-07-12 09:09:16', '2021-07-12 09:09:16', 1, 1, 0);
INSERT INTO dish_flavor (id, dish_id, name, value, create_time, update_time, create_user, update_user, is_deleted) VALUES (1413389684020682754, 1413342036832100354, '温度', '["常温","冷藏"]', '2021-07-09 15:12:18', '2021-07-09 15:12:18', 1, 1, 0);


create table employee
(
    id          bigint      null,
    name        varchar(32) null,
    username    varchar(32) null,
    password    varchar(64) null,
    phone       varchar(11) null,
    sex         varchar(2)  null,
    id_number   varchar(18) null,
    status      int         null,
    create_time datetime    null,
    update_time datetime    null,
    create_user bigint      null,
    update_user bigint      null
);

INSERT INTO employee (id, name, username, password, phone, sex, id_number, status, create_time, update_time, create_user, update_user) VALUES (1, '管理员', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '13812312312', '1', '110101199001010047', 1, '2021-05-06 17:20:07', '2023-12-08 13:30:31', 1, 1);
INSERT INTO employee (id, name, username, password, phone, sex, id_number, status, create_time, update_time, create_user, update_user) VALUES (1732737175644205058, '冯佳和', '2113042621', 'e10adc3949ba59abbe56e057f20f883e', '18334501074', '1', '140428200210130010', 1, '2023-12-07 20:21:50', '2023-12-19 16:13:43', 1, 1);



create table operate_log
(
    id            bigint auto_increment
        primary key,
    operate_user  bigint        null,
    operate_time  datetime      null,
    class_name    varchar(100)  null,
    method_name   varchar(100)  null,
    method_params varchar(1000) null,
    return_value  varchar(2000) null,
    cost_time     bigint        null
);


create table order_detail
(
    id          bigint         null,
    name        varchar(50)    null,
    image       varchar(100)   null,
    order_id    bigint         null,
    dish_id     bigint         null,
    setmeal_id  bigint         null,
    dish_flavor varchar(50)    null,
    number      int            null,
    amount      decimal(10, 2) null
);

INSERT INTO order_detail (id, name, image, order_id, dish_id, setmeal_id, dish_flavor, number, amount) VALUES (1747173171550912514, '儿童套餐A计划', '0a3b3288-3446-4420-bbff-f263d0c02d8e.jpg', 1747173171550912513, null, 1415580119015145474, null, 1, 40.00);
INSERT INTO order_detail (id, name, image, order_id, dish_id, setmeal_id, dish_flavor, number, amount) VALUES (1747173171613827074, '麻辣水煮鱼', '1fdbfbf3-1d86-4b29-a3fc-46345852f2f8.jpg', 1747173171550912513, 1397854652581064706, null, '不要葱,微辣', 1, 138.00);


create table orders
(
    id              bigint         null,
    number          varchar(50)    null,
    status          int            null,
    user_id         bigint         null,
    address_book_id bigint         null,
    order_time      datetime       null,
    checkout_time   datetime       null,
    pay_method      int            null,
    amount          decimal(10, 2) null,
    remark          varchar(100)   null,
    phone           varchar(255)   null,
    address         varchar(255)   null,
    user_name       varchar(255)   null,
    consignee       varchar(255)   null
);

INSERT INTO orders (id, number, status, user_id, address_book_id, order_time, checkout_time, pay_method, amount, remark, phone, address, user_name, consignee) VALUES (1747173171550912513, '1747173171550912513', 2, 1738402096889384961, 1747173138608848898, '2024-01-16 16:25:20', '2024-01-16 16:25:20', 1, 178.00, '', '15903551284', '无', null, '冯');


create table setmeal
(
    id          bigint         null,
    category_id bigint         null,
    name        varchar(64)    null,
    price       decimal(10, 2) null,
    status      int            null,
    code        varchar(32)    null,
    description varchar(512)   null,
    image       varchar(255)   null,
    create_time datetime       null,
    update_time datetime       null,
    create_user bigint         null,
    update_user bigint         null,
    is_deleted  int            null
);

INSERT INTO setmeal (id, category_id, name, price, status, code, description, image, create_time, update_time, create_user, update_user, is_deleted) VALUES (1415580119015145474, 1413386191767674881, '儿童套餐A计划', 4000.00, 1, '', '', '0a3b3288-3446-4420-bbff-f263d0c02d8e.jpg', '2021-07-15 15:52:55', '2021-07-15 15:52:55', 1415576781934608386, 1415576781934608386, 0);



create table setmeal_dish
(
    id          bigint         null,
    setmeal_id  varchar(32)    null,
    dish_id     varchar(32)    null,
    name        varchar(32)    null,
    price       decimal(10, 2) null,
    copies      int            null,
    sort        int            null,
    create_time datetime       null,
    update_time datetime       null,
    create_user bigint         null,
    update_user bigint         null,
    is_deleted  int            null
);

INSERT INTO setmeal_dish (id, setmeal_id, dish_id, name, price, copies, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1415580119052894209, '1415580119015145474', '1397862198033297410', '老火靓汤', 49800.00, 1, 0, '2021-07-15 15:52:55', '2021-07-15 15:52:55', 1415576781934608386, 1415576781934608386, 0);
INSERT INTO setmeal_dish (id, setmeal_id, dish_id, name, price, copies, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1415580119061282817, '1415580119015145474', '1413342036832100354', '北冰洋', 500.00, 1, 0, '2021-07-15 15:52:55', '2021-07-15 15:52:55', 1415576781934608386, 1415576781934608386, 0);
INSERT INTO setmeal_dish (id, setmeal_id, dish_id, name, price, copies, sort, create_time, update_time, create_user, update_user, is_deleted) VALUES (1415580119069671426, '1415580119015145474', '1413385247889891330', '米饭', 200.00, 1, 0, '2021-07-15 15:52:55', '2021-07-15 15:52:55', 1415576781934608386, 1415576781934608386, 0);


create table shopping_cart
(
    id          bigint         null,
    name        varchar(50)    null,
    image       varchar(100)   null,
    user_id     bigint         null,
    dish_id     bigint         null,
    setmeal_id  bigint         null,
    dish_flavor varchar(50)    null,
    number      int            null,
    amount      decimal(10, 2) null,
    create_time datetime       null
);


create table user
(
    id        bigint       null,
    name      varchar(50)  null,
    phone     varchar(100) null,
    sex       varchar(2)   null,
    id_number varchar(18)  null,
    avatar    varchar(500) null,
    status    int          null
);

INSERT INTO user (id, name, phone, sex, id_number, avatar, status) VALUES (1737419810509336577, null, '13303450011', null, null, null, 1);
INSERT INTO user (id, name, phone, sex, id_number, avatar, status) VALUES (1738402096889384961, null, '18334501074', null, null, null, 1);
INSERT INTO user (id, name, phone, sex, id_number, avatar, status) VALUES (1738816285655056385, null, '13303450012', null, null, null, 1);
INSERT INTO user (id, name, phone, sex, id_number, avatar, status) VALUES (1738844654094409730, null, '17696292683', null, null, null, 1);

