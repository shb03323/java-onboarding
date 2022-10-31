package onboarding;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Problem7 {
    private static final Set<String> userFriends = new HashSet<>();
    private static final Map<String, Integer> recommendScoreMap = new HashMap<>();

    public static List<String> solution(String user, List<List<String>> friends, List<String> visitors) {
        List<String> answer = Collections.emptyList();
        setFriendsOfUser(user, friends);

        increaseMutualScore(user, friends);

        return answer;
    }

    private static void setFriendsOfUser(String user, List<List<String>> friends) {
        friends.stream()
                .filter(friend -> friend.contains(user))
                .forEach(userFriends::addAll);

        userFriends.remove(user);
    }

    private static void increaseMutualScore(String user, List<List<String>> friends) {
        friends.stream()
                .filter(friend ->
                        userFriends.contains(friend.get(0)) || userFriends.contains(friend.get(1)))
                .filter(friend ->
                        !(friend.get(0).equals(user) || friend.get(1).equals(user)))
                .forEach(friend -> {
                    increaseOnePersonScore(friend.get(0), 10);
                    increaseOnePersonScore(friend.get(1), 10);
                });
    }

    private static void increaseOnePersonScore(String person, Integer score) {
        if (userFriends.contains(person)) {
            return;
        }
        if (recommendScoreMap.containsKey(person)) {
            int scoreOfPerson = recommendScoreMap.get(person);
            recommendScoreMap.put(person, scoreOfPerson + score);
        } else {
            recommendScoreMap.put(person, score);
        }
    }
}
