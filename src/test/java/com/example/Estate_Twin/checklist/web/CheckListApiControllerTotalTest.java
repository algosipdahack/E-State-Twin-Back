package com.example.Estate_Twin.checklist.web;


import com.example.Estate_Twin.checklist.data.dao.impl.CheckListDAOImpl;
import com.example.Estate_Twin.checklist.data.entity.CheckList;
import com.example.Estate_Twin.checklist.data.entity.RepairType;
import com.example.Estate_Twin.checklist.service.CheckListService;
import com.example.Estate_Twin.checklist.service.impl.CheckListServiceImpl;
import com.example.Estate_Twin.checklist.web.dto.CheckListResponseDto;
import com.example.Estate_Twin.checklist.web.dto.CheckListSaveRequestDto;
import com.example.Estate_Twin.estate.domain.entity.EstateType;
import com.example.Estate_Twin.estate.domain.entity.TransactionType;
import com.example.Estate_Twin.user.domain.dao.UserDAO;
import com.example.Estate_Twin.user.domain.entity.AuthProvider;
import com.example.Estate_Twin.user.domain.entity.Role;
import com.example.Estate_Twin.user.domain.entity.User;
import com.example.Estate_Twin.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Slf4j
public class CheckListApiControllerTotalTest {
    @Autowired
    private CheckListApiController checkListApiController;
    @Autowired
    private CheckListDAOImpl checkListDAO;
    @Autowired
    private CheckListService checkListService;
    @Autowired
    private UserDAO userDAO;
    User user;
    CheckListSaveRequestDto saveRequestDto;
    CheckList checkList;
    @BeforeEach
    public void setUp() throws Exception {
        String date = "2022-10-22";
        user = User.builder()
                .borough("강남구")
                .estateType(EstateType.OFFICETELS)
                .transactionType(TransactionType.LEASE)
                .phone("01055555555")
                .birthday(LocalDate.parse(date, DateTimeFormatter.ISO_DATE))
                .name("조소연")
                .role(Role.USER)
                .authProvider(AuthProvider.KAKAO)
                .email("sophia5460@gmail.com")
                .refreshToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzb3BoaWE1NDYwQG5hdmVyLmNvbSIsInVzZXJuYW1lIjoic29waGlhNTQ2MEBuYXZlci5jb20iLCJpYXQiOjE2Njc3MzE5NTIsImV4cCI6MjI3MjUzMTk1Mn0.ryVrNvH5pxToayF_4qkpYXIDRd13KxDEBQR6hW7hZg-d3juGj18Ps4LEJzCg-HX58Xqth_0FVYTpVkoG_kcuQg")
                .build();
        saveRequestDto = new CheckListSaveRequestDto("하자부위","content",LocalDate.of(2022,06,12), "REPAIR","photo");
        checkList = CheckList.builder()
                .checkListPhoto("checklist_photo")
                .repairType(RepairType.REPAIR)
                .checkListContent("content")
                .flawPart("하자부위")
                .repairDate(LocalDate.of(2022,06,12))
                .build();
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void 체크리스트_생성() {
        checkListService.saveCheckList(user,saveRequestDto,1L,1L);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void 체크리스트_컨펌() throws InterruptedException {
        checkListService.confirmCheckList(1L,2L,user,1500);
    }

    @Test
    @Transactional
    public void postCheckList() throws Exception {
            Thread tx1 = new Thread(new UpdatePostTask(2L, 1500));
            //Thread tx2 = new Thread(new UpdatePostTask(2L, 2000));
            tx1.setName("1.5 초 대기 스레드");
            //tx2.setName("2.0 초 대기 스레드");
            tx1.start();
            //tx2.start();
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                log.error("main thread sleep error", e);
            }
    }
    private class UpdatePostTask implements Runnable {

        private Long postId;

        private Integer waitingTime;

        public UpdatePostTask(Long postId, Integer waitingTime) {
            this.postId = postId;
            this.waitingTime = waitingTime;
        }

        @Override
        public void run() {
            try {
                checkListService.confirmCheckList(1L,2L,userDAO.findUserById(1L));

            } catch (Exception e) {
                log.error("update thread sleep error", e);
            }
        }
    }
    /*@Test
    public void confirmCheckList() throws Exception {
        checkListApiController.confirmCheckList()
    }*/
}
