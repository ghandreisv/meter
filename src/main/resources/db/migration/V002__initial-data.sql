INSERT INTO ADDRESSES (id, street, house_number, city, country)
VALUES ('0ad46436-532d-4bfb-92e6-654e036b6cda', 'Lincoln street', '15-A', 'New York', 'USA');
INSERT INTO ADDRESSES (id, street, house_number, city, country)
VALUES ('2fbd8b26-41ca-4f94-bc3f-b5d0cf759b51', 'Santana street', '10', 'Sao Paulo', 'Brazil');

INSERT INTO CLIENTS (id, first_name, last_name, address_id)
VALUES ('5d4a1603-5456-4340-9e1f-fb6861573570', 'John', 'Cena', '0ad46436-532d-4bfb-92e6-654e036b6cda');
INSERT INTO CLIENTS (id, first_name, last_name, address_id)
VALUES ('97ee3d5f-e84f-4274-bc1f-54474a77b3eb', 'Ayrton', 'Senna', '2fbd8b26-41ca-4f94-bc3f-b5d0cf759b51');

INSERT INTO METERS (id, address_id)
VALUES ('4758677d-bb43-4f21-9bdf-98349b30003e', '0ad46436-532d-4bfb-92e6-654e036b6cda');
INSERT INTO METERS (id, address_id)
VALUES ('4a64d51d-b5f4-4193-aab0-a344db0ca8a4', '2fbd8b26-41ca-4f94-bc3f-b5d0cf759b51');

-- readings for client 1
INSERT INTO METER_READINGS (id, meter_id, date, value)
VALUES ('77188b30-a5bb-4f63-b36c-20d2ec1bb911', '4758677d-bb43-4f21-9bdf-98349b30003e', '2019-01-01', 10);
INSERT INTO METER_READINGS (id, meter_id, date, value)
VALUES ('1af59c17-2ecb-48b9-8bf1-e1bd3ba6c1f0', '4758677d-bb43-4f21-9bdf-98349b30003e', '2019-02-01', 20);
INSERT INTO METER_READINGS (id, meter_id, date, value)
VALUES ('926344aa-cc87-49fb-b133-9e418e32a04c', '4758677d-bb43-4f21-9bdf-98349b30003e', '2019-12-01', 30);
INSERT INTO METER_READINGS (id, meter_id, date, value)
VALUES ('3cb6c5e1-bdb0-4c9e-8bc9-701b840368a0', '4758677d-bb43-4f21-9bdf-98349b30003e', '2020-01-01', 20);
INSERT INTO METER_READINGS (id, meter_id, date, value)
VALUES ('2396a347-10d3-484c-be13-35366c6aa3fa', '4758677d-bb43-4f21-9bdf-98349b30003e', '2020-05-01', 40);
INSERT INTO METER_READINGS (id, meter_id, date, value)
VALUES ('871ae00f-de53-4af3-8d1d-6660339d7bbd', '4758677d-bb43-4f21-9bdf-98349b30003e', '2020-07-01', 60);

-- readings for client 2
INSERT INTO METER_READINGS (id, meter_id,date, value)
VALUES ('c30150ee-be03-41e4-a67d-1150610f4840', '4a64d51d-b5f4-4193-aab0-a344db0ca8a4', '2019-01-01', 5);
INSERT INTO METER_READINGS (id, meter_id, date, value)
VALUES ('415af591-facd-477a-a703-cfe96bd66486', '4a64d51d-b5f4-4193-aab0-a344db0ca8a4', '2019-03-01', 15);
INSERT INTO METER_READINGS (id, meter_id, date, value)
VALUES ('b7ea89ef-8356-4733-815d-b189e9f6e40b', '4a64d51d-b5f4-4193-aab0-a344db0ca8a4', '2019-06-01', 25);
INSERT INTO METER_READINGS (id, meter_id, date, value)
VALUES ('4b50e8ab-ca88-4f1e-a8af-d5c51dd0a1dd', '4a64d51d-b5f4-4193-aab0-a344db0ca8a4', '2020-01-01', 3);
INSERT INTO METER_READINGS (id, meter_id, date, value)
VALUES ('a535d77b-c7d2-4da3-b416-3338fcba2ea7', '4a64d51d-b5f4-4193-aab0-a344db0ca8a4', '2020-04-01', 13);
INSERT INTO METER_READINGS (id, meter_id, date, value)
VALUES ('7b3b2ab9-1ebc-413c-b82d-47ddadbed57b', '4758677d-bb43-4f21-9bdf-98349b30003e', '2020-12-01', 23);

