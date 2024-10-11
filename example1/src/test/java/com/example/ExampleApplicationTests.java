package com.example;

import com.example.product.ProductEntity;
import com.example.product.ProductService;
import com.example.review.request.ReviewInsertDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ExampleApplicationTests {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ProductService productService;
    Long productId;  // 저장된 상품의 ID를 담을 변수

    String 조회URL = "/products/%d/reviews?cursor=%d&size=%d";

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void beforeEach() {
        //given
        productId = productService.productSave();
    }


    @Test
    void contextLoads() {
    }

    @Test
    @DisplayName("통합테스트 : 상품 조회 성공")
    public void 상품조회() throws Exception {
        //when
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.get(String.format(조회URL, productId, 0, 5)));
        //then
        result.andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
    }

    @Test
    @DisplayName("통합테스트 실패 : 상품 조회 페이지 사이즈 0")
    public void 상품조회페이지사이즈0() throws Exception {
        //when
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.get(String.format(조회URL, productId, 0, 0)));
        //then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest()).andDo(print());
    }

    @Test
    @DisplayName("통합 테스트 : 리뷰 저장 성공")
    public void 리뷰저장_통합테스트() throws Exception {
        // given
        Long userId = 1L;
        Float score = 4.5f;
        String content = "리뷰 내용 테스트입니다.";
        MockMultipartFile image = new MockMultipartFile(
                "image", "test-image.jpg", "image/jpeg", "dummy image data".getBytes()
        );

        String url = String.format("/products/%d/reviews", productId);

        // when
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.multipart(url)
                        .file(image)
                        .param("userId", String.valueOf(userId))
                        .param("score", String.valueOf(score))
                        .param("content", content)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
        );

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }
    @Test
    @DisplayName("통합 테스트 : 리뷰 저장 실패")
    public void 리뷰저장_통합테스트실패() throws Exception {
        // given
        Long userId = 1L;
        Float score = 6.5f;
        String content = "리뷰 내용 테스트입니다.";
        MockMultipartFile image = new MockMultipartFile(
                "image", "test-image.jpg", "image/jpeg", "dummy image data".getBytes()
        );

        String url = String.format("/products/%d/reviews", 100000);

        // when
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.multipart(url)
                        .file(image)
                        .param("userId", String.valueOf(userId))
                        .param("score", String.valueOf(score))
                        .param("content", content)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
        );

        // then
        result.andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andDo(print());
    }

}
