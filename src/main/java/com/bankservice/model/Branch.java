package com.bankservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "branchs")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long branchId;

    private String branchName;
    private String location;
    private Long Contact;

    @OneToMany(mappedBy = "branch",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Account> accounts;

}
