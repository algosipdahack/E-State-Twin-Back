package com.example.Estate_Twin.domain.estate;

import com.example.Estate_Twin.domain.BaseTimeEntity;
import com.example.Estate_Twin.domain.asset.Asset;
import com.example.Estate_Twin.domain.asset.Category;
import com.example.Estate_Twin.domain.checklist.CheckList;
import com.example.Estate_Twin.domain.checklist.RepairType;
import com.example.Estate_Twin.domain.media.Media;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "estate")
public class Estate extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "estate_id")
    //매물번호 202007070001 이런식으로 할 예정
    private long id;

    @Embedded private EstateNo estateNo;

    @OneToMany(
            mappedBy = "estate",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    private List<Media> estateMedia = new ArrayList<>();

    //리스트에서 보여줄 썸네일
    @Column
    private String estateThumbNail;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @OneToMany(
            mappedBy = "estate",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true // DB에서 함께 삭제됨
    )
    private List<CheckList> checkList = new ArrayList<>();


    @Enumerated(EnumType.STRING)
    private Rank rank;

    @Column
    private String model;

    @Column
    private String arCam;

    @Builder // 빌더 형태로 만들어줌
    public Estate(List<Media> estateMedia, String content, List<CheckList> checkList, Rank rank,
                  String model, String arCam
    ) {//생성자
        this.estateMedia = estateMedia;
        this.content = content;
        this.checkList = checkList;
        this.rank = rank;
        this.model = model;
        this.arCam = arCam;
    }
}
