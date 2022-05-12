package univ.tuit.backend.config.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private CacheManager cacheManager;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("On application event is OK");
        cacheManager.getCacheNames().parallelStream().forEach(n -> {
            System.out.println(n);
        });
    }
}
