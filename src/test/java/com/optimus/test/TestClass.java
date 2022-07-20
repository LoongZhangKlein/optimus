package com.optimus.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@Slf4j
class Menu implements Serializable {
    public static void main(String[] args) {
        int i=0;
        int b=++i;
        log.debug(" ++++>{}",b);
    }

}
