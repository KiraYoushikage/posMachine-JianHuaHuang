package pos.machine;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author KiraYous
 * @version V1.0
 * @Package pos.machine
 * @date 2022/7/19 21:15
 */
public class Receipt {
    Map<String,Receipt.ReceiptItem> receiptItems;
    int total;

    public void setReceiptItems(Map<String, ReceiptItem> receiptItems) {
        this.receiptItems = receiptItems;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Map<String, ReceiptItem> getReceiptItems() {
        return receiptItems;
    }

    public int getTotal() {
        return total;
    }

    public static class  ReceiptItem{
        String name;
        int quantity;
        int price;
        int subTotal;

        public ReceiptItem() {
        }

        public ReceiptItem(String name, int quantity, int price, int subTotal) {
            this.name = name;
            this.quantity = quantity;
            this.price = price;
            this.subTotal = subTotal;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getSubTotal() {
            return subTotal;
        }

        public void setSubTotal(int subTotal) {
            this.subTotal = subTotal;
        }
    }
}
