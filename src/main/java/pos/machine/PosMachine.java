package pos.machine;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PosMachine {

    List<ItemInfo> itemInfos;

    public String printReceipt(List<String> barcodes) {
        itemInfos=ItemDataLoader.loadAllItemInfos();
        Receipt receipt=buildReceipt();
        barcodes.forEach(barcode->{
            ItemInfo itemInfo=findItemInfosByBarCodes(barcode);
            addToMap(itemInfo,receipt);
        });
        calculateTotal(receipt);
        return renderReceipt(receipt);
    }

    public void addToMap(ItemInfo itemInfo, Receipt receipt) {
        Map<String, Receipt.ReceiptItem> map=receipt.getReceiptItems();
        String barcode=itemInfo.getBarcode();
        Receipt.ReceiptItem receiptItem=map.getOrDefault(barcode,new Receipt.ReceiptItem(itemInfo.getName(), 0, itemInfo.getPrice(), 0));
        int quantity=receiptItem.getQuantity();
        int subTotal= receiptItem.getSubTotal();
        int price= receiptItem.getPrice();
        receiptItem.setQuantity(++quantity);
        receiptItem.setSubTotal(subTotal+price);
        map.put(barcode,receiptItem);
    }

    public ItemInfo findItemInfosByBarCodes(String barcodes){
        for (ItemInfo itemInfo : itemInfos) {
            if (itemInfo.getBarcode().equals(barcodes)) return itemInfo;
        }
        return null;
    }
    public Receipt buildReceipt(){
        Receipt receipt=new Receipt();
        receipt.setReceiptItems(new LinkedHashMap<>());
        return receipt;
    }

    public void calculateTotal(Receipt receipt){
        Map<String, Receipt.ReceiptItem> map=receipt.getReceiptItems();
        map.forEach((key,value)-> receipt.setTotal(receipt.getTotal()+ value.getSubTotal())
        );
    }

    public String renderReceipt(Receipt receipt){
        String result = "***<store earning no money>Receipt***\n";
        Map<String, Receipt.ReceiptItem> map=receipt.getReceiptItems();
        for (Map.Entry<String, Receipt.ReceiptItem> entry : map.entrySet()) {
            Receipt.ReceiptItem value=entry.getValue();
            result+="Name: "+value.getName()+", Quantity: "+value.getQuantity()+", Unit price: "+value.getPrice()+" (yuan), Subtotal: "+value.getSubTotal()+" (yuan)\n";
        }
        result+= "----------------------\n";
        result+= "Total: "+receipt.getTotal()+" (yuan)\n" + "**********************";
        return result;
    }

}
