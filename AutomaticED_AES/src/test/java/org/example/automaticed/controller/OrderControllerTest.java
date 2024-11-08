package org.example.automaticed.controller;

import org.example.automaticed.entity.OrderEntity;
import org.example.automaticed.service.IOrderService;
import org.example.automaticed.util.CryptoUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private IOrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

//    @Test
//    void testEncrypt() throws Exception {
//        String plaintext = "test";
//        String algorithm = "AES";
//        String encryptedText = "encryptedTest";
//
//        when(CryptoUtil.encrypt(plaintext, algorithm)).thenReturn(encryptedText);
//
//        mockMvc.perform(get("/encrypt")
//                        .param("plaintext", plaintext)
//                        .param("algorithm", algorithm))
//                .andExpect(status().isOk())
//                .andExpect(content().string(encryptedText));
//    }

//    @Test
//    void testDecrypt() throws Exception {
//        String ciphertext = "encryptedTest";
//        String algorithm = "AES";
//        String decryptedText = "test";
//
//        when(CryptoUtil.decrypt(ciphertext, algorithm)).thenReturn(decryptedText);
//
//        mockMvc.perform(get("/decrypt")
//                        .param("ciphertext", ciphertext)
//                        .param("algorithm", algorithm))
//                .andExpect(status().isOk())
//                .andExpect(content().string(decryptedText));
//    }

    @Test
    void testSaveAll() throws Exception {
        List<OrderEntity> orders = Collections.singletonList(new OrderEntity());
        when(orderService.save(anyInt())).thenReturn(orders);

        mockMvc.perform(get("/saveAll")
                        .param("count", "5"))
                .andExpect(status().isOk());
    }

    @Test
    void testGet() throws Exception {
        Long orderId = 1L;
        String orderDetails = "Order Details";

        when(orderService.get(orderId)).thenReturn(orderDetails);

        mockMvc.perform(get("/get")
                        .param("orderId", orderId.toString()))
                .andExpect(status().isOk())
                .andExpect(content().string(orderDetails));
    }

    @Test
    void testGetOrderEntity() throws Exception {
        Long orderId = 1L;
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(orderId);

        when(orderService.getOrderEntity(orderId)).thenReturn(orderEntity);

        mockMvc.perform(get("/getOrderEntity")
                        .param("orderId", orderId.toString()))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"orderId\":1}"));
    }

    @Test
    void testGetAllByPage() throws Exception {
        int pageNo = 0;
        int pageSize = 1;
        OrderEntity orderEntity = new OrderEntity();
        Page<OrderEntity> page = new PageImpl<>(Collections.singletonList(orderEntity), PageRequest.of(pageNo, pageSize), 1);

        when(orderService.findPaginated(pageNo, pageSize)).thenReturn(page);

        mockMvc.perform(get("/getAllByPage")
                        .param("pageNo", String.valueOf(pageNo))
                        .param("pageSize", String.valueOf(pageSize)))
                .andExpect(status().isOk());
    }

    @Test
    void testSaveByOneself() throws Exception {
        OrderEntity orderEntity = new OrderEntity();
        List<OrderEntity> orderList = Collections.singletonList(orderEntity);

        when(orderService.saveByOneself(orderList)).thenReturn(orderList);

        mockMvc.perform(post("/saveByOneself")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"orderId\":1}]"))
                .andExpect(status().isOk());
    }
}
