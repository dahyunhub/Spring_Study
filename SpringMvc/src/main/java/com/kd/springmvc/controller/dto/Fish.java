package com.kd.springmvc.controller.dto;

import java.util.Date;

import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Fish {
   
   @NonNull
   private String name;
   private Date sale_date;
   
   private double kg;
   private double price_per_kg;
}