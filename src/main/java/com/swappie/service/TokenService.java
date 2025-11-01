package com.swappie.service;

import com.swappie.domain.AccessToken;
import com.swappie.domain.Account;
import com.swappie.domain.RefreshToken;
import com.swappie.dto.TokenList;
import com.swappie.repository.AccessTokenRepository;
import com.swappie.repository.RefreshTokenRepository;
import com.swappie.utils.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final AccessTokenRepository accessTokenRepo;
    private final RefreshTokenRepository refreshTokenRepo;

    @Transactional
    public TokenList generateAndSaveToken(Account account) {
        String access = TokenUtils.generateToken();
        String refresh = TokenUtils.generateToken();

        accessTokenRepo.save(AccessToken.builder()
            .token(access)
            .accountId(account.getId())
            .expiresIn(TokenUtils.ACCESS_TOKEN_TTL)
            .build()
        );

        refreshTokenRepo.save(RefreshToken.builder()
            .token(refresh)
            .account(account)
            .build()
        );

        return new TokenList(refresh, access);
    }

}
