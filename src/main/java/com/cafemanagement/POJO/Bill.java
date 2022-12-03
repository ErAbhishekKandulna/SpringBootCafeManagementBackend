package com.cafemanagement.POJO;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;
import lombok.NoArgsConstructor;

//custom query
@NamedQuery(name = "Bill.getAllBills",query = "select b from Bill b order by b.id desc")

@NamedQuery(name = "Bill.getBillByUserName",query = "select b from Bill b where b.createdBy=:username order by b.id desc")

@Entity
@Data
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@Table(name = "bill")
public class Bill implements Serializable 
{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "uuid")
	private String uuid;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "contactnumber")
	private String contactNumber;
	
	@Column(name = "paymentmethod")
	private String paymentMethod;
	
	@Column(name = "total")
	private Integer total;
	
	@Column(name = "productdetails",columnDefinition = "json")
	private String productDetail;
	
	@Column(name = "createdby")
	private String createdBy;
}
