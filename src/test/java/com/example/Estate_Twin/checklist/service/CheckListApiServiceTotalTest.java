package com.example.Estate_Twin.checklist.service;


import com.example.Estate_Twin.checklist.data.dao.impl.CheckListDAOImpl;
import com.example.Estate_Twin.checklist.data.entity.CheckList;
import com.example.Estate_Twin.checklist.data.entity.RepairType;
import com.example.Estate_Twin.checklist.data.repository.CheckListRepository;
import com.example.Estate_Twin.checklist.web.CheckListApiController;
import com.example.Estate_Twin.checklist.web.dto.CheckListSaveRequestDto;
import com.example.Estate_Twin.estate.domain.entity.EstateType;
import com.example.Estate_Twin.estate.domain.entity.TransactionType;
import com.example.Estate_Twin.user.domain.entity.AuthProvider;
import com.example.Estate_Twin.user.domain.entity.Role;
import com.example.Estate_Twin.user.domain.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Slf4j
public class CheckListApiServiceTotalTest {
    @Autowired
    private CheckListApiController checkListApiController;
    @Autowired
    private CheckListDAOImpl checkListDAO;
    @Autowired
    private CheckListRepository checkListRepository;
    @Autowired
    private CheckListService checkListService;
    User owner;
    User broker;
    CheckListSaveRequestDto saveRequestDto;
    CheckList checkList;
    @BeforeEach
    public void setUp() throws Exception {
        String date = "2022-10-22";
        owner = User.builder()
                .borough("?????????")
                .estateType(EstateType.OFFICETELS)
                .transactionType(TransactionType.LEASE)
                .phone("01055555555")
                .birthday(LocalDate.parse(date, DateTimeFormatter.ISO_DATE))
                .name("?????????")
                .role(Role.USER)
                .authProvider(AuthProvider.KAKAO)
                .email("sophia5460@gmail.com")
                .refreshToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzb3BoaWE1NDYwQG5hdmVyLmNvbSIsInVzZXJuYW1lIjoic29waGlhNTQ2MEBuYXZlci5jb20iLCJpYXQiOjE2Njc3MzE5NTIsImV4cCI6MjI3MjUzMTk1Mn0.ryVrNvH5pxToayF_4qkpYXIDRd13KxDEBQR6hW7hZg-d3juGj18Ps4LEJzCg-HX58Xqth_0FVYTpVkoG_kcuQg")
                .build();
        broker = User.builder()
                .borough("?????????")
                .estateType(EstateType.OFFICETELS)
                .transactionType(TransactionType.LEASE)
                .phone("01055555555")
                .birthday(LocalDate.parse(date, DateTimeFormatter.ISO_DATE))
                .name("?????????")
                .role(Role.USER)
                .authProvider(AuthProvider.KAKAO)
                .email("sophia5460@gmail.com")
                .refreshToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzb3BoaWE1NDYwQG5hdmVyLmNvbSIsInVzZXJuYW1lIjoic29waGlhNTQ2MEBuYXZlci5jb20iLCJpYXQiOjE2Njc3MzE5NTIsImV4cCI6MjI3MjUzMTk1Mn0.ryVrNvH5pxToayF_4qkpYXIDRd13KxDEBQR6hW7hZg-d3juGj18Ps4LEJzCg-HX58Xqth_0FVYTpVkoG_kcuQg")
                .build();
        broker.setIsBroker();
        saveRequestDto = new CheckListSaveRequestDto("????????????","content",LocalDate.of(2022,06,12), "REPAIR","photo");
        checkList = CheckList.builder()
                .checkListPhoto("checklist_photo")
                .repairType(RepairType.REPAIR)
                .checkListContent("content")
                .flawPart("????????????")
                .repairDate(LocalDate.of(2022,06,12))
                .build();
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void ???????????????_??????() {
        checkListService.saveCheckList(owner,saveRequestDto,1L,1L);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void ???????????????_??????() throws InterruptedException {
        checkListService.confirmCheckList(1L,owner);
    }

    @Test
    @Transactional
    @DisplayName("broker, owner??? ????????? confirm??? ???????????? tot_confirm??? y??? ?????????")
    void checkListConfirm_withOptimisticLockingHandling() throws InterruptedException {
        //given
        ExecutorService service = Executors.newFixedThreadPool(2);
        //when
        service.execute(() -> {
            try {
                checkListService.confirmCheckList(1L, owner);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        service.execute(() -> {
            try {
                checkListService.confirmCheckList(1L, broker);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Thread.sleep(1000);
        //then
        final CheckList checkList1 = checkListRepository.findById(7L).get();
        assertEquals(2, checkList1.getVersion());
        assertEquals(true, checkList1.getTotalConfirmYN());
    }
}
