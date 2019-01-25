package com.lay.smartframework.util.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

public class BatchUtil {
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	private static AtomicLong order_seq = new AtomicLong(0L);
	private static String order_seq_prefix = "T";
	private static AtomicLong order_item_seq = new AtomicLong(0L);
	private static String order_item_seq_prefix = "D";
	private static AtomicLong pay_seq = new AtomicLong(0L);
	private static String pay_seq_prefix = "P";
	private static AtomicLong refund_seq = new AtomicLong(0L);
	private static String refund_seq_prefix = "R";
	private static AtomicLong upload_seq = new AtomicLong(0L);
	private static String upload_seq_prefix = "U";
	private static AtomicLong ticket_seq = new AtomicLong(0L);
	private static String ticket_seq_prefix = "C";
	
	private static String getSeq(String prefix, AtomicLong seq) {
		Date date =new Date();
		return String.format("%s%s%03d", prefix, simpleDateFormat.format(date).substring(2), (int) seq.getAndIncrement() % 1000);
	}
	
	public static String getOrder() {
		return getSeq(order_seq_prefix, order_seq);
	}

	public static String getOrderItem() {
		return getSeq(order_item_seq_prefix, order_item_seq);
	}

	public static String getPayOrder() {
		return getSeq(pay_seq_prefix, pay_seq);
	}

	public static String getRefundOrder() {
		return getSeq(refund_seq_prefix, refund_seq);
	}
	
	public static String getUpload() {
		return getSeq(upload_seq_prefix, upload_seq);
	}
	
	public static String getTicketNo() {
		return getSeq(ticket_seq_prefix, ticket_seq);
	}
	
	public static void main(String[] args) {
		System.out.println(simpleDateFormat.format(new Date()).substring(2));
		
		String str="I11775220170308204724959";
		String pre=str.substring(0,str.length()-17);
		String last=str.substring(str.length()-3);
		System.out.println(pre+"      "+last);
		
		for (int i=0;i<10;i++) {
			System.out.println("orderItem=" + getOrderItem());
			System.out.println("pay=" + getPayOrder());
			System.out.println("refund=" + getRefundOrder());
		}
	}
}
