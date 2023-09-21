package com.example.fastcampusmysql.domain.post;

import com.example.fastcampusmysql.domain.post.entity.Post;
import com.example.fastcampusmysql.domain.post.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
public class PostBulkInsertTest {
    @Autowired
    private PostRepository postRepository;

    @Test
    public void bulkInsert() {
        var easyRandom = PostFixtureFactory.get(
                3L,
                LocalDate.of(2022,1,1),
                LocalDate.of(2022,2,1)
        );

        var posts = IntStream.range(0, 10000*100)
                .parallel()
                .mapToObj(i -> easyRandom.nextObject(Post.class))
                .toList();

        postRepository.bulkInsert(posts);
    }
}
