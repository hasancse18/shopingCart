package com.custominterceptor.demo.shpingcart.service.image;

import com.custominterceptor.demo.shpingcart.bean.ImageBean;
import com.custominterceptor.demo.shpingcart.model.Image;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

//import java.awt.*;

@Service
public interface ImageService {
    Image getImageById(Long id);
    void deleteImageById( Long id);
    public List<ImageBean> saveImage(List<MultipartFile> files, Long id);
    void updateImage(MultipartFile file, Long id);
}
