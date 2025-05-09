package capstone.bownling.gameservice.config.mongo;

import capstone.bownling.gameservice.src.entity.document.GamePlayLiveEntity;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;


@Component
public class GamePlayEntityListener extends AbstractMongoEventListener<GamePlayLiveEntity> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<GamePlayLiveEntity> event) {
        GamePlayLiveEntity entity = event.getSource();

        if (entity.getId() == null || entity.getId().isEmpty()) {
            String compoundId = entity.getMatchRoomId() + "-" +
                    entity.getUserId() + "-" +
                    entity.getFrame() + "-" +
                    entity.getRound();
            entity.setId(compoundId);
        }
    }
}