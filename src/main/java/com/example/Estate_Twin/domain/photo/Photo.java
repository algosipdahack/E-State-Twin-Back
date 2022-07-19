package com.example.Estate_Twin.domain.photo;

import com.example.Estate_Twin.domain.BaseTimeEntity;
import com.example.Estate_Twin.domain.estate.Estate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "file")
public class Photo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "estate_id")
    private Estate estate;

    @Column(nullable = false)
    private String origFileName;

    @Column(nullable = false)
    private String filePath;

    private Long fileSize;

    @Builder
    public Photo(String origFileName, String filePath, Long fileSize) {
        this.origFileName = origFileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

    public void setEstate(Estate estate) {
        this.estate = estate;

        if(!estate.getEstatePhoto().contains(this)) {
            estate.getEstatePhoto().add(this);
        }
    }

}
