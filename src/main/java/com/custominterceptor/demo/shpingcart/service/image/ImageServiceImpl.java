package com.custominterceptor.demo.shpingcart.service.image;

import com.custominterceptor.demo.shpingcart.bean.ImageBean;
import com.custominterceptor.demo.shpingcart.exception.ResourceNotFoundException;
import com.custominterceptor.demo.shpingcart.model.Image;
import com.custominterceptor.demo.shpingcart.model.Product;
import com.custominterceptor.demo.shpingcart.repository.ImageRepository;
import com.custominterceptor.demo.shpingcart.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import java.awt.*;

@Service
public class ImageServiceImpl implements ImageService{

    @Autowired
    ImageRepository imageRepository;
    @Autowired
    ProductService productService;
    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Image Not Found id = "+id+" "));
    }

    @Override
    public void deleteImageById(Long id) {
        imageRepository.findById(id).ifPresentOrElse(imageRepository :: delete, ()-> {
            throw new ResourceNotFoundException("Image Not Found id = "+id+" ");
        });

    }

    @Override
    public List<ImageBean> saveImage(List<MultipartFile> files, Long id) {

        Product product = productService.getProductById(id);
        List<ImageBean> imageBeanList = new ArrayList<>();
        for(MultipartFile file : files){
            try {
                Image image = new Image();
                image.setFileName(file.getOriginalFilename());
                image.setFileType(file.getContentType());
                image.setImage(new SerialBlob(file.getBytes()));
                image.setProduct(product);

                String downLoadUrl = "/api/v1/images/image/download/";
                image.setDownloadUrl(downLoadUrl);
                Image savedImages =imageRepository.save(image);

                String newDownLoadUrl = "/api/v1/images/image/download/"+savedImages.getId();
                savedImages.setDownloadUrl(newDownLoadUrl);
                Image savedImage = imageRepository.save(savedImages);


                ImageBean imageBean = new ImageBean();

                imageBean.setId(savedImage.getId());
                imageBean.setFileName(savedImage.getFileName());
                imageBean.setDownloadUrl(downLoadUrl);

                imageBeanList.add(imageBean);





            }catch(Exception e){
                e.printStackTrace();
            }
        }

        return imageBeanList;
    }

    @Override
    public void updateImage(MultipartFile file, Long id) {
        Image image = getImageById(id);
        try{
            image.setFileName(file.getOriginalFilename());
            image.setFileType(file.getContentType());
            image.setImage(new SerialBlob(file.getBytes()));
        }catch (IOException | SQLException e){
            throw new RuntimeException(e.getMessage());
        }

    }
}
