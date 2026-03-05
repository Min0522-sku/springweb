package example.day06.entity;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GoodsService {
    // { "gname" : "콜라", "gdesc" : "탄산음료", "gprice" : 1500 }
    @Autowired private GoodsRepository goodsRepository;

    public boolean 저장(GoodsDto goodsDto){
        //GoodsEntity goodsEntity = goodsDto.toEntity();
        //GoodsEntity saved = goodsRepository.save(goodsEntity);
        // or
        GoodsEntity saved = goodsRepository.save(goodsDto.toEntity());
        if(saved.getGno() >= 1 ) return true;
        return false;
    }

    // 수정 ** @Transactional 필수 **
    // 수정시 여러개 setter 사용함으로 단일 실행
    @Transactional
    // {"gno":1, "gname" : "콜라", "gdesc" : "탄산음료", "gprice" : 1500 }
    public boolean 수정(GoodsDto goodsDto){
        int updatePk = goodsDto.getGno();
        Optional<GoodsEntity> optional = goodsRepository.findById(updatePk);
        if(optional.isPresent()){
            GoodsEntity updateEntity = optional.get();
            updateEntity.setGdesc(goodsDto.getGdesc());
            updateEntity.setGname(goodsDto.getGname());
            updateEntity.setGprice(goodsDto.getGprice());
            return true;
        }else return false;
    }
}
