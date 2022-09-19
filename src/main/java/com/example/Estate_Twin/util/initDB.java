package com.example.Estate_Twin.util;

import com.example.Estate_Twin.address.data.repository.AddressRepository;
import com.example.Estate_Twin.asset.data.repository.AssetRepository;
import com.example.Estate_Twin.checklist.data.repository.CheckListRepository;
import com.example.Estate_Twin.contractstate.domain.repository.ContractStateRepository;
import com.example.Estate_Twin.estate.domain.repository.EstateHitRepository;
import com.example.Estate_Twin.estate.domain.repository.EstateRepository;
import com.example.Estate_Twin.house.domain.repository.HouseRepository;
import com.example.Estate_Twin.media.domain.repository.MediaRepository;
import com.example.Estate_Twin.user.domain.repository.BrokerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
/*
@Component
@RequiredArgsConstructor
public class InitDb {
/
    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final AddressRepository addressRepository;
        private final AssetRepository assetRepository;
        private final BrokerRepository brokerRepository;
        private final CheckListRepository checkListRepository;
        private final ContractStateRepository contractStateRepository;
        private final EstateRepository estateRepository;
        private final EstateHitRepository estateHitRepository;
        private final HouseRepository houseRepository;
        private final MediaRepository mediaRepository;
        private final EntityManager em;

        public void dbInit1() {
            Address address = new Address("인천","남동구","만수동");
            Member admin = new Member("admin","admin","123","01000000000",address);
            Member m1 = new Member("박철준","cjfwns","123","01000000000",address);
            Member m2 = new Member("강훈","gns","123","01000000000",address);
            admin.setAdmin();
            admin.setPassword(admin.getPassword());
            m1.setPassword(m1.getPassword());
            m2.setPassword(m2.getPassword());
            em.persist(admin);
            em.persist(m1);s
            em.persist(m2);

            //test data
//            Book book = new Book("https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F5416922%3Ftimestamp%3D20220218170906"
//            ,"달러구트 꿈 백화점","이미예","팩토리나인","1165341905 9791165341909");
//            book.setMember(admin);
//            bookRepository.save(book);
//            for(int i = 1; i <= 100; ++i)
//            {
//                Book b = new Book("img","book"+i,"author",
//                        "publisher","isbn");
//                b.setMember(admin);
//                em.persist(b);
//            }
        }
    }
}*/
