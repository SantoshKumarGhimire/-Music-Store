package com.emusicstore.controller.admin;

import com.emusicstore.model.Product;
import com.emusicstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/admin")
public class AdminProducts {
    @Autowired
    ProductService productService;
    private Path path;

    @RequestMapping("/product/addProduct")

    public String addProduct(Model model) {
        Product product = new Product();
        product.setProductCondition("new");
        product.setProductCategory("instrument");
        product.setProductStatus("active");
        model.addAttribute(product);

        return "addProduct";


    }

    @RequestMapping(value = "/product/addProduct", method = RequestMethod.POST)

    public String addProductPost(@Valid @ModelAttribute("product") Product product,
                                 BindingResult result,
                                 HttpServletRequest request) {

        if (result.hasErrors()) {
            return "addProduct";
        }

        productService.addProduct(product);

        MultipartFile productImage = product.getProductImage();

        String rootDirectory = request.getSession().getServletContext().getRealPath("/");

        path = Paths.get(rootDirectory + "/WEB-INF/resources/images/" + product.getProductId() + ".png");

        if (productImage != null && !productImage.isEmpty()) {
            try {
                productImage.transferTo(new File(path.toString()));
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Product image saving failed", e);
            }
        }

        return "redirect:/admin/productInventory";

    }

    @RequestMapping(value = "/product/editProduct/{productId}", method = RequestMethod.GET)
    public String editProduct(@PathVariable(name = "productId") int productId, Model model) {

        Product product = productService.getProductById(productId);

        model.addAttribute("product", product);

        return "editProduct";
    }

    @RequestMapping(value = "/product/editProduct", method = RequestMethod.POST)

    public String editProductPost(@Valid @ModelAttribute("product") Product product,
                                  BindingResult result, HttpServletRequest request) {

        if (result.hasErrors()) {
            return "editProduct";
        }

        productService.editProduct(product);

        MultipartFile productImage = product.getProductImage();

        String rootDirectory = request.getSession().getServletContext().getRealPath("/");

        path = Paths.get(rootDirectory + "/WEB-INF/resources/images/" + product.getProductId() + ".png");

        if (productImage != null && !productImage.isEmpty()) {
            try {
                productImage.transferTo(new File(path.toString()));
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Product image saving failed", e);
            }
        }

        return "redirect:/admin/productInventory";
    }

    @RequestMapping("/product/deleteProduct/{productId}")

    public String deleteProduct(@PathVariable int productId, HttpServletRequest request) {

        productService.deleteProduct(productService.getProductById(productId));

        String rootDirectory = request.getSession().getServletContext().getRealPath("/");

        path = Paths.get(rootDirectory + "/WEB-INF/resources/images/" + productId + ".png");

        if (Files.exists(path)) {
            try {
                Files.delete(path);
            } catch (IOException e) {
                e.printStackTrace();

            }



        }

        return "redirect:/admin/productInventory";
    }
}
