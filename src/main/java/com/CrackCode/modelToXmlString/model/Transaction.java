package com.CrackCode.modelToXmlString.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement
public class Transaction {
    private String reason;
    private BigDecimal amount;
}
