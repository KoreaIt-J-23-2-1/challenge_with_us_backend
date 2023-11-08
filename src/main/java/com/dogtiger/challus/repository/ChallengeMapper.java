package com.dogtiger.challus.repository;

import com.dogtiger.challus.entity.Challenge;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface ChallengeMapper {
    public int saveChallenge(Challenge challenge);
    public Challenge getChallengeByChallengeId(int challengeId);
    public int getLikeState(int challengeId);
    public int getUserLikeState(Challenge challenge);
    public int insertLike(Challenge challenge);
    public int cancelLike(Challenge challenge);
    public int challengeDelete(int challengeId);
    public int getChallengeStatus(int challengeId, int userId);
    public int getChallengeAtmospher(int challengeId, int userId);
    public int challengeApplicable(int challengeId, int userId);
}
