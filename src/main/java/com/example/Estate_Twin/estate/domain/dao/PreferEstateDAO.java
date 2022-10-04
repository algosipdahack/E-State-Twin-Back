package com.example.Estate_Twin.estate.domain.dao;

import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.user.domain.entity.User;

public interface PreferEstateDAO {
    PreferEstate savePreferEstate(Estate estate, User user, Preference prefer);
    boolean existPreferEstate(Long estateId, Long userId, Preference prefer);
}
