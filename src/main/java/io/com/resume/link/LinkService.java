package io.com.resume.link;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LinkService {

    private final LinkRepository repository;

    public List<Link> findByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    public Link create(Link link) {
        return repository.save(link);
    }
}
