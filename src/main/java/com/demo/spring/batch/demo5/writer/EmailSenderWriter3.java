package com.demo.spring.batch.demo5.writer;

import org.springframework.batch.item.ItemWriter;

import com.demo.spring.batch.demo5.dto.EmployeeDTO;

import java.util.List;

public class EmailSenderWriter3 implements ItemWriter<EmployeeDTO> {
    @Override
    public void write(List<? extends EmployeeDTO> list) throws Exception {
        System.out.println("Email send Step 3 successfully to all the employees. "+ list.size());
    }
}

