INSERT INTO address (id, city, borough, town, complexName, block, unit, roadName, mainBuildingNumber, subBuildingNumber, buildingName) VALUES (1, '서울특별시', '강남구', '개포동', '1단지', '1동', '1호', '1로', 1, 1, '1건물');
INSERT INTO address (id, city, borough, town, complexName, block, unit, roadName, mainBuildingNumber, subBuildingNumber, buildingName) VALUES (2, '경기도', '고양시', '화정동', '2단지', '2동', '2호', '2로', 2, 2, '2건물');
INSERT INTO address (id, city, borough, town, complexName, block, unit, roadName, mainBuildingNumber, subBuildingNumber, buildingName) VALUES (3, '강원도', '고성군', '간성읍', '3단지', '3동', '3호', '3로', 3, 3, '3건물');
INSERT INTO address (id, city, borough, town, complexName, block, unit, roadName, mainBuildingNumber, subBuildingNumber, buildingName) VALUES (4, '부산광역시', '강서구', '강동동', '4단지', '4동', '4호', '4로', 4, 4, '4건물');
INSERT INTO address (id, city, borough, town, complexName, block, unit, roadName, mainBuildingNumber, subBuildingNumber, buildingName) VALUES (5, '인천광역시', '계양구', '갈현동', '5단지', '5동', '5호', '5로', 5, 5, '5건물');

INSERT INTO asset (id, assetName, productName, category, createdDate, modifiedDate) VALUES (1, '의자', '체어클럽', 'FURNITURE', 1, 'LG', '2022-06-12 22:44:10', '2022-06-12 23:44:54');
INSERT INTO asset (id, assetName, productName, category, createdDate, modifiedDate) VALUES (2, '식탁', '토미', 'FURNITURE', 2, 'LG', '2022-06-12 22:44:10', '2022-06-12 23:44:54');
INSERT INTO asset (id, assetName, productName, category, createdDate, modifiedDate) VALUES (3, '벽지', '코스모스', 'INTERIOR', 3, 'LG', '2022-06-12 22:44:10', '2022-06-12 23:44:54');
INSERT INTO asset (id, assetName, productName, category, createdDate, modifiedDate) VALUES (4, '변기', '대림바스', 'BATHROOM', 4, 'LG', '2022-06-12 22:44:10', '2022-06-12 23:44:54');
INSERT INTO asset (id, assetName, productName, category, createdDate, modifiedDate) VALUES (5, '침대', '에이', 'FURNITURE', 5, 'LG', '2022-06-12 22:44:10', '2022-06-12 23:44:54');

INSERT INTO broker (id, businessName, agentName, brokerageRegistrationNumber, businessRegistrationNumber, businessLicense, brokerageRegistrationLicense, user_id) VALUES (1, '1회사', '1에이전트', 1, 1, 'http://url', 'http://url', 1);
INSERT INTO broker (id, businessName, agentName, brokerageRegistrationNumber, businessRegistrationNumber, businessLicense, brokerageRegistrationLicense, user_id) VALUES (3, '3회사', '3에이전트', 3, 3, 'http://url', 'http://url', 3);
INSERT INTO broker (id, businessName, agentName, brokerageRegistrationNumber, businessRegistrationNumber, businessLicense, brokerageRegistrationLicense, user_id) VALUES (5, '5회사', '5에이전트', 5, 5, 'http://url', 'http://url', 5);

INSERT INTO checklist (id, flawPart, category, checkListContent, repairDate, repairType, brokerConfirmYN, tanentConfirmYN, ownerConfirmYN, asset_id, createdDate, modifiedDate) VALUES (1, '1하자부위', 'HOMEAPPLIANCES', '1', '2022-06-12 23:44:54', 'REPAIR', false, false, false, 1, '2022-06-12 22:44:10' ,'2022-06-12 23:44:54');
INSERT INTO refresh_token (id, user_id, value, created_at, updated_at) VALUES (2, 2, 'refresh_token2', '2022-06-13 21:52:34', '2022-06-13 21:52:35');
INSERT INTO refresh_token (id, user_id, value, created_at, updated_at) VALUES (3, 3, 'refresh_token3', '2022-06-13 21:52:34', '2022-06-13 21:52:35');
INSERT INTO refresh_token (id, user_id, value, created_at, updated_at) VALUES (4, 4, 'refresh_token4', '2022-06-13 21:52:34', '2022-06-13 21:52:35');
INSERT INTO refresh_token (id, user_id, value, created_at, updated_at) VALUES (5, 5, 'refresh_token5', '2022-06-13 21:52:34', '2022-06-13 21:52:35');

INSERT INTO coach (id, user_id, introduce, is_active, created_at, updated_at) VALUES (1, 1, '안녕하세요. 테스트1 코치입니다.', true, '2022-06-13 21:57:17', '2022-06-13 21:57:17');
INSERT INTO coach (id, user_id, introduce, is_active, created_at, updated_at) VALUES (2, 3, '안녕하세요. 테스트3 코치입니다.', true, '2022-06-13 21:57:18', '2022-06-13 21:57:18');
INSERT INTO coach (id, user_id, introduce, is_active, created_at, updated_at) VALUES (3, 4, '안녕하세요. 테스트4 코치입니다.', true, '2022-06-13 21:57:18', '2022-06-13 21:57:19');
INSERT INTO coach (id, user_id, introduce, is_active, created_at, updated_at) VALUES (4, 5, '안녕하세요. 테스트5 코치입니다.', false, '2022-06-13 21:57:20', '2022-06-13 21:57:19');

INSERT INTO notification (id, user_id, coach_id, title, content, noti_type, is_read, is_active, created_at, updated_at) VALUES (1, 1, 3, '제목1', '알림 내용', 'PLAYER', false, true, '2022-06-13 22:17:08', '2022-06-13 22:17:09');
INSERT INTO notification (id, user_id, coach_id, title, content, noti_type, is_read, is_active, created_at, updated_at) VALUES (2, 3, 1, '제목2', '알림 내용2', 'COACH', true, true, '2022-06-13 22:17:18', '2022-06-13 22:17:19');
INSERT INTO notification (id, user_id, coach_id, title, content, noti_type, is_read, is_active, created_at, updated_at) VALUES (3, 1, 2, '제목3', '알림 내용', 'PLAYER', false, true, '2022-06-13 22:17:28', '2022-06-13 22:17:29');
INSERT INTO notification (id, user_id, coach_id, title, content, noti_type, is_read, is_active, created_at, updated_at) VALUES (4, 4, 3, '제목4', '알림 내용', 'PLAYER', true, true, '2022-06-13 22:17:38', '2022-06-13 22:17:39');

INSERT INTO review (id, user_id, coach_id, lecture_name, content, is_active, created_at, updated_at, is_public) VALUES (1, 1, 3, '클래스1', '테스트3은 좋은 코치입니다!', true, '2022-06-13 22:19:14', '2022-06-13 22:19:15', true);
INSERT INTO review (id, user_id, coach_id, lecture_name, content, is_active, created_at, updated_at, is_public) VALUES (2, 2, 1, '클래스2', '테스트1 코치는 친절했어요~', true, '2022-06-13 22:19:14', '2022-06-13 22:19:15', true);
INSERT INTO review (id, user_id, coach_id, lecture_name, content, is_active, created_at, updated_at, is_public) VALUES (3, 4, 3, '클래스3', '테스트3 코치는 좀 별로였습니다', true, '2022-06-13 22:19:16', '2022-06-13 22:19:16', false);
INSERT INTO review (id, user_id, coach_id, lecture_name, content, is_active, created_at, updated_at, is_public) VALUES (4, 3, 1, '클래스4', '테스트1 코치가 최고에요~', true, '2022-06-13 22:19:18', '2022-06-13 22:19:17', true);

INSERT INTO category (id, category_name, is_active) VALUES (1, 'PM', true);
INSERT INTO category (id, category_name, is_active) VALUES (2, 'DESIGN', true);
INSERT INTO category (id, category_name, is_active) VALUES (3, 'DEVELOP', true);
INSERT INTO category (id, category_name, is_active) VALUES (4, 'MARKETING', true);
INSERT INTO category (id, category_name, is_active) VALUES (5, 'LANGUAGE', true);
INSERT INTO category (id, category_name, is_active) VALUES (6, 'ETC', true);

INSERT INTO lecture_information (id, coach_id, category_id, topic, introduce, price, is_active, created_at, updated_at) VALUES (1, 1, 4, '마케팅 수업', '효과적인 마케팅에 대해 배웁니다', 1, true, '2022-06-13 22:39:40', '2022-06-13 22:39:54');
INSERT INTO lecture_information (id, coach_id, category_id, topic, introduce, price, is_active, created_at, updated_at) VALUES (2, 2, 3, '자바 개발', '자바를 자바라', 1, true, '2022-06-13 22:39:41', '2022-06-13 22:39:54');
INSERT INTO lecture_information (id, coach_id, category_id, topic, introduce, price, is_active, created_at, updated_at) VALUES (3, 3, 2, '그래픽 디자인', '그래픽을 그래그래', 1, true, '2022-06-13 22:39:42', '2022-06-13 22:39:55');
INSERT INTO lecture_information (id, coach_id, category_id, topic, introduce, price, is_active, created_at, updated_at) VALUES (4, 4, 1, '좋은 기획이란 무엇인가', '기획이 제일 쉬웠어요 사실 뻥이야', 1, false, '2022-06-13 22:39:42', '2022-06-13 22:39:55');
INSERT INTO lecture_information (id, coach_id, category_id, topic, introduce, price, is_active, created_at, updated_at) VALUES (5, 1, 6, '요리 클래스', '맛있는 요리 만들기', 1, true, '2022-06-13 22:39:42', '2022-06-13 22:39:55');

INSERT INTO lecture (id, lecture_info_id, start_at, end_at, state, is_review_written, is_active, created_at, updated_at) VALUES (1, 1, '2022-06-08 22:44:10', '2022-06-08 23:44:04', 'DONE', true, true, '2022-06-08 22:44:10', '2022-06-08 22:44:10');
INSERT INTO lecture (id, lecture_info_id, start_at, end_at, state, is_review_written, is_active, created_at, updated_at) VALUES (2, 1, '2022-06-09 22:44:10', '2022-06-09 23:44:04', 'ON_GOING', false, true, '2022-06-08 22:44:11', '2022-06-08 22:44:11');
INSERT INTO lecture (id, lecture_info_id, start_at, end_at, state, is_review_written, is_active, created_at, updated_at) VALUES (3, 1, '2022-06-10 22:44:10', '2022-06-10 23:44:04', 'ON_BOARD', false, true, '2022-06-08 22:44:12', '2022-06-08 22:44:12');
INSERT INTO lecture (id, lecture_info_id, start_at, end_at, state, is_review_written, is_active, created_at, updated_at) VALUES (4, 2, '2022-06-12 22:44:10', '2022-06-12 23:44:04', 'ON_BOARD', false, true, '2022-06-08 22:44:10', '2022-06-08 22:44:10');
INSERT INTO lecture (id, lecture_info_id, start_at, end_at, state, is_review_written, is_active, created_at, updated_at) VALUES (5, 3, '2022-06-08 22:44:10', '2022-06-13 23:44:04', 'DONE', true, true, '2022-06-08 22:44:10', '2022-06-08 22:44:10');
INSERT INTO lecture (id, lecture_info_id, start_at, end_at, state, is_review_written, is_active, created_at, updated_at) VALUES (6, 4, '2022-06-13 22:44:10', '2022-06-13 23:44:04', 'ON_BOARD', false, true, '2022-06-08 22:44:10', '2022-06-08 22:44:10');

INSERT INTO tag (id, lecture_info_id, content, is_active, created_at, updated_at) VALUES (1, 1, '마케팅', true, '2022-06-13 22:42:23', '2022-06-13 22:42:23');
INSERT INTO tag (id, lecture_info_id, content, is_active, created_at, updated_at) VALUES (2, 1, '쉬워요', true, '2022-06-13 22:42:24', '2022-06-13 22:42:24');
INSERT INTO tag (id, lecture_info_id, content, is_active, created_at, updated_at) VALUES (3, 2, '언어', true, '2022-06-13 22:42:25', '2022-06-13 22:42:25');
INSERT INTO tag (id, lecture_info_id, content, is_active, created_at, updated_at) VALUES (4, 2, '자바개발', true, '2022-06-13 22:42:26', '2022-06-13 22:42:26');
INSERT INTO tag (id, lecture_info_id, content, is_active, created_at, updated_at) VALUES (5, 3, '전문가', false, '2022-06-13 22:42:27', '2022-06-13 22:42:27');

INSERT INTO lecture_image (id, lecture_info_id, lecture_img_url, is_active, created_at, updated_at) VALUES (1, 1, 'http://lecture1.img.url', true, '2022-06-13 21:26:58', '2022-06-13 21:27:01');
INSERT INTO lecture_image (id, lecture_info_id, lecture_img_url, is_active, created_at, updated_at) VALUES (2, 1, 'http://lecture1.img2.url', false, '2022-06-13 21:26:59', '2022-06-13 21:27:00');
INSERT INTO lecture_image (id, lecture_info_id, lecture_img_url, is_active, created_at, updated_at) VALUES (3, 2, 'http://lecture2.img.url', false, '2022-06-13 21:27:02', '2022-06-13 21:27:01');
INSERT INTO lecture_image (id, lecture_info_id, lecture_img_url, is_active, created_at, updated_at) VALUES (4, 3, 'http://lecture3.img.url', true, '2022-06-13 21:27:03', '2022-06-13 21:27:03');
INSERT INTO lecture_image (id, lecture_info_id, lecture_img_url, is_active, created_at, updated_at) VALUES (5, 4, 'http://lecture4.img.url', true, '2022-06-13 21:27:04', '2022-06-13 21:27:04');
INSERT INTO lecture_image (id, lecture_info_id, lecture_img_url, is_active, created_at, updated_at) VALUES (6, 5, 'http://lecture5.img.url', true, '2022-06-13 21:27:05', '2022-06-13 21:27:05');
INSERT INTO lecture_image (id, lecture_info_id, lecture_img_url, is_active, created_at, updated_at) VALUES (7, 2, 'http://lecture2.img2.url', true, '2022-06-13 21:27:06', '2022-06-13 21:27:06');

INSERT INTO form (id, lecture_id, user_id, content, state, is_active, expiration_date, created_at, updated_at) VALUES (1, 1, 4, '신청서를 받아주세요!', 'ACCEPT', true, '2022-06-16 22:48:17', '2022-06-13 22:48:17', '2022-06-13 22:48:17');
INSERT INTO form (id, lecture_id, user_id, content, state, is_active, expiration_date, created_at, updated_at) VALUES (2, 2, 3, '제 신청서를 받아주세요!', 'ACCEPT', true, '2022-06-16 22:48:18', '2022-06-13 22:48:18', '2022-06-13 22:48:18');
INSERT INTO form (id, lecture_id, user_id, content, state, is_active, expiration_date, created_at, updated_at) VALUES (3, 1, 2, '안받아주셔도 되요', 'REJECT', true, '2022-06-16 22:48:19', '2022-06-13 22:48:19', '2022-06-13 22:48:19');
INSERT INTO form (id, lecture_id, user_id, content, state, is_active, expiration_date, created_at, updated_at) VALUES (4, 4, 1, '신청서를 받아주세요!', 'REQUEST', true, '2022-06-16 22:48:20', '2022-06-13 22:48:20', '2022-06-13 22:48:20');
INSERT INTO form (id, lecture_id, user_id, content, state, is_active, expiration_date, created_at, updated_at) VALUES (5, 3, 3, '신청 거절해주세요', 'REJECT', true, '2022-06-16 22:51:03', '2022-06-13 22:51:03', '2022-06-13 22:51:04');
INSERT INTO form (id, lecture_id, user_id, content, state, is_active, expiration_date, created_at, updated_at) VALUES (6, 3, 4, '신청합니다!', 'REQUEST', true, '2022-06-16 22:51:04', '2022-06-13 22:51:04', '2022-06-13 22:51:05');
INSERT INTO form (id, lecture_id, user_id, content, state, is_active, expiration_date, created_at, updated_at) VALUES (7, 4, 2, '신청서를 받아주세요!', 'REQUEST', true, '2022-06-16 22:51:05', '2022-06-13 22:51:05', '2022-06-13 22:51:06');

