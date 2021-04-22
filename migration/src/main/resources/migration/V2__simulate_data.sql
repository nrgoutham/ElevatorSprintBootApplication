INSERT INTO ELEVATOR
VALUES ('e4e671ae-d357-4d49-ae64-cbf5bfed4ba3', 'E1', '1', '{1, 3, 5,7, 9}', 'UP');
INSERT INTO ELEVATOR
VALUES ('c8fc8b1b-ed49-40bc-b607-1961a77d9611', 'E2', '2', '{2, 4, 6,8, 10}', 'DOWN');
INSERT INTO ELEVATOR
VALUES ('35ebd664-46ac-48e3-89f4-46cc95873dd7', 'E3', '1', '{1, 3, 5,7, 9}', 'STOPPED');
INSERT INTO ELEVATOR
VALUES ('59061bbd-5b55-4c8c-ada4-1937ade62504', 'E4', '2', '{2, 4, 6,8, 10}', 'STOPPED');
INSERT INTO ELEVATOR
VALUES ('51495dab-92e9-48ac-b834-f5aeccb7eeaa', 'E5', '1', '{1, 3, 5,7, 9}', 'OUT_OF_SERVICE');
INSERT INTO ELEVATOR
VALUES ('a307f457-0d8a-4229-9877-131202479dd5', 'E6', '2', '{2, 4, 6,8, 10}', 'OUT_OF_SERVICE');


INSERT INTO BUILDING
VALUES ('1fd5257d-96a5-4631-818c-def734e94c7f', 'B1', 'Galway Office', '{e4e671ae-d357-4d49-ae64-cbf5bfed4ba3,c8fc8b1b-ed49-40bc-b607-1961a77d9611, 35ebd664-46ac-48e3-89f4-46cc95873dd7,
                                                                       59061bbd-5b55-4c8c-ada4-1937ade62504,51495dab-92e9-48ac-b834-f5aeccb7eeaa,a307f457-0d8a-4229-9877-131202479dd5}');

INSERT INTO USERS
VALUES ('0ed317dd-ae43-44e6-b866-aa0b7b02c47c', 'Gowtham', '{1fd5257d-96a5-4631-818c-def734e94c7f}');
