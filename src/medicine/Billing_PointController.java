package medicine;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.animation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.bson.Document;

public class Billing_PointController implements Initializable {

    @FXML private StackPane Billing_StackPane;
    @FXML private VBox sidebar;
    @FXML private Button close_Btn;
    @FXML private AnchorPane Dashboard;
    @FXML private TreeView<String> Tree_View;
    @FXML private AnchorPane add_panes, Tree_Dash, add_pane, update_pane, delete_pane, view_product, history_pane;
    @FXML private AnchorPane day_pane, week_pane, monthly_pane;
    @FXML private Label dashboardClock;

    @FXML private TextField txtItemCode, txtProductName, txtBatchNo, txtBonus, txtCompanyName, txtRetail, txtSalePrice;
    @FXML private TextField txtGroup, txtCompanyDis, txtCostPrice, txtSaleTax, txtCompanyName2, txtPiecesPick;
    @FXML private TextField txtTradePrice, txtDiscount, txtBonusPrice;

    @FXML private TextField txtItemCodeT, txtProductNameT, txtBatchNoT, txtBonusT, txtCompanyCodeT, txtRetailT;
    @FXML private TextField txtSalePriceT, txtGroupT, txtCompanyDisT, txtCostPriceT, txtSaleTaxT, txtCompanyNameT;
    @FXML private TextField txtPiecesPickT, txtTradePriceT, txtDiscountT, txtBonusPriceT;

    @FXML private TableView<Product> tableProducts;
    @FXML private TableColumn<Product, String> colItemCode, colProductName, colBatchNo, colCompanyName;
    @FXML private TableColumn<Product, String> colSalePrice, colCompanyDis, colCostPrice, colSaleTax;
    @FXML private TableColumn<Product, String> colTradePrice, colDiscount;

    @FXML private Button btnAdd, updatebutton, clearbutton;

    private boolean sidebarVisible = true;
    @FXML
    private Button mene_button;
    @FXML
private TableView<Product> tabledelete;

@FXML
private TableColumn<Product, String> colItemCodeD;
@FXML
private TableColumn<Product, String> colProductNameD;
@FXML
private TableColumn<Product, String> colBatchNoD;
@FXML
private TableColumn<Product, String> colSalePriceD;
@FXML
private TableColumn<Product, String> colCompanyDisD;
@FXML
private TableColumn<Product, String> colTradePriceD;
@FXML
private TableColumn<Product, String> colDiscountD;
@FXML
private TableColumn<Product, String> colSaleTaxD;
@FXML
private TableColumn<Product, String> colCompanyNameD;
    @FXML
    private TextField txtItemCodeD;
    @FXML
    private TextField txtProductNameD;
    @FXML
    private TextField txtBatchNoD;
    @FXML
    private TextField txtBonusD;
    @FXML
    private TextField txtCompanyCodeD;
    @FXML
    private TextField txtRetailD;
    @FXML
    private TextField txtSalePriceD;
    @FXML
    private TextField txtGroupD;
    @FXML
    private TextField txtCompanyDisD;
   @FXML
    private TextField txtCostPriceD;
    @FXML
    private TextField txtSaleTaxD;
    @FXML
    private TextField txtCompanyNameD;
    @FXML
    private TextField txtPiecesPickD;
//    private TextField txtTradePriceD;
//    private TextField txtDiscountD;
    @FXML
    private TextField txtBonusPriceD;
    @FXML
    private TextField txtExpiryDateD;
    @FXML
    private TextField txtQuantityD;
    @FXML
    private Button btnDeleteD;
    @FXML
    private Button btnClearD;
    @FXML
    private TableView<Product> Finalviewtable;
    @FXML
    private TableColumn<Product, String> itemcodeL;
    @FXML
    private TableColumn<Product, String> itemnamel;
    @FXML
    private TableColumn<Product, String> batchnol;
    @FXML
    private TableColumn<Product, String> salepricel;
    @FXML
    private TableColumn<Product, String> companydisl;
    @FXML
    private TableColumn<Product, String> tradepricel;
    @FXML
    private TableColumn<Product, String> discountl;
    @FXML
    private TableColumn<Product, String> costpricel;
    @FXML
    private TableColumn<Product, String> saletaxl;
    @FXML
    private TableColumn<Product, String> companynamel;


    @FXML
    public void close() {
        System.exit(0);
    }

    @FXML
    private void insertData() {
        try {
            MongoDatabase db = Database.getConnection();
            MongoCollection<Document> collection = db.getCollection("DataEntry");

            Document doc = new Document("itemCode", txtItemCode.getText())
                    .append("productName", txtProductName.getText())
                    .append("batchNo", txtBatchNo.getText())
                    .append("bonus", txtBonus.getText())
                    .append("companyName", txtCompanyName.getText())
                    .append("retail", txtRetail.getText())
                    .append("salePrice", txtSalePrice.getText())
                    .append("group", txtGroup.getText())
                    .append("companyDis", txtCompanyDis.getText())
                    .append("costPrice", txtCostPrice.getText())
                    .append("saleTax", txtSaleTax.getText())
                    .append("companyName2", txtCompanyName2.getText())
                    .append("piecesPick", txtPiecesPick.getText())
                    .append("tradePrice", txtTradePrice.getText())
                    .append("discount", txtDiscount.getText())
                    .append("bonusPrice", txtBonusPrice.getText());
            collection.insertOne(doc);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Data Inserted");
            alert.setContentText("Product added successfully!");
            alert.showAndWait();

            clearFields();
            loadAllProducts(); // Refresh TableView after insert
            loadAllProducts3();
            loadAllProducts2();

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Insert Failed");
            alert.setContentText("Error inserting data: " + e.getMessage());
            alert.showAndWait();
        }
    }

    private void clearFields() {
        txtItemCode.clear(); txtProductName.clear(); txtBatchNo.clear(); txtBonus.clear();
        txtCompanyName.clear(); txtRetail.clear(); txtSalePrice.clear(); txtGroup.clear();
        txtCompanyDis.clear(); txtCostPrice.clear(); txtSaleTax.clear(); txtCompanyName2.clear();
        txtPiecesPick.clear(); txtTradePrice.clear(); txtDiscount.clear(); txtBonusPrice.clear();
    }

    public void loadAllProducts() {
        try {
            MongoDatabase db = Database.getConnection();
            MongoCollection<Document> collection = db.getCollection("DataEntry");

            ObservableList<Product> productList = FXCollections.observableArrayList();

            for (Document doc : collection.find()) {
                productList.add(new Product(
                        doc.getString("itemCode"), doc.getString("productName"),
                        doc.getString("batchNo"), doc.getString("bonus"),
                        doc.getString("companyName"), doc.getString("retail"),
                        doc.getString("salePrice"), doc.getString("group"),
                        doc.getString("companyDis"), doc.getString("costPrice"),
                        doc.getString("saleTax"), doc.getString("companyName2"),
                        doc.getString("piecesPick"), doc.getString("tradePrice"),
                        doc.getString("discount"), doc.getString("bonusPrice")
                ));
            }

            tableProducts.setItems(productList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    
    
    
    ///================================update working =====================================//
private void updateData() {
    try {
        // 1️⃣ Connect to MongoDB
        MongoDatabase db = Database.getConnection();
        MongoCollection<Document> collection = db.getCollection("DataEntry");

        // 2️⃣ Create a filter to find the document to update (by itemCode)
        Document filter = new Document("itemCode", txtItemCodeT.getText());

        // 3️⃣ Create the updated values
        Document updateValues = new Document()
            .append("productName", txtProductNameT.getText())
            .append("batchNo", txtBatchNoT.getText())
           .append("salePrice", txtSalePriceT.getText())
            .append("companyDis", txtCompanyDisT.getText())
            .append("tradePrice", txtTradePriceT.getText())
            .append("discount", txtDiscountT.getText())
            .append("costPrice", txtCostPriceT.getText())
            .append("saleTax", txtSaleTaxT.getText())
            .append("companyName", txtCompanyCodeT.getText())
            .append("bonus", txtBonusT.getText())
            .append("companyName", txtCompanyCodeT.getText())
            .append("retail", txtRetailT.getText())
            .append("group", txtGroupT.getText())
            .append("saleTax", txtSaleTaxT.getText())
            .append("companyName2", txtCompanyNameT.getText())
            .append("piecesPick", txtPiecesPickT.getText())
            .append("bonusPrice", txtBonusPriceT.getText());

        // 4️⃣ Wrap with $set
        Document updateDoc = new Document("$set", updateValues);

        // 5️⃣ Update the document in MongoDB
        collection.updateOne(filter, updateDoc);

        // 6️⃣ Show success alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Data Updated");
        alert.setContentText("Product updated successfully!");
        alert.showAndWait();
         clearUpdateFields();
        // Optional: reload table data
        loadAllProducts();

    } catch (Exception e) {
        e.printStackTrace();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Update Failed");
        alert.setContentText("Error updating data: " + e.getMessage());
        alert.showAndWait();
    }
}
    @FXML
    public void clearUpdateFields() {
   txtItemCodeT.setText("");
txtProductNameT.setText("");
txtBatchNoT.setText("");
txtBonusT.setText("");
txtCompanyCodeT.setText("");
txtRetailT.setText("");
txtSalePriceT.setText("");
txtGroupT.setText("");
txtCompanyDisT.setText("");
txtCostPriceT.setText("");
txtSaleTaxT.setText("");
txtCompanyNameT.setText("");
txtPiecesPickT.setText("");
txtTradePriceT.setText("");
txtDiscountT.setText("");
txtBonusPriceT.setText("");
 tableProducts.getSelectionModel().clearSelection();
}

    
    
    
    
    
    
    //===============================================Delete working
    public void loadAllProducts2() {
        try {
            MongoDatabase db = Database.getConnection();
            MongoCollection<Document> collection = db.getCollection("DataEntry");

            ObservableList<Product> productList = FXCollections.observableArrayList();

            for (Document doc : collection.find()) {
                productList.add(new Product(
                        doc.getString("itemCode"), doc.getString("productName"),
                        doc.getString("batchNo"), doc.getString("bonus"),
                        doc.getString("companyName"), doc.getString("retail"),
                        doc.getString("salePrice"), doc.getString("group"),
                        doc.getString("companyDis"), doc.getString("costPrice"),
                        doc.getString("saleTax"), doc.getString("companyName2"),
                        doc.getString("piecesPick"), doc.getString("tradePrice"),
                        doc.getString("discount"), doc.getString("bonusPrice")
                ));
            }

            tabledelete.setItems(productList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDeleteRow() {
        Product selectedItem = tabledelete.getSelectionModel().getSelectedItem();

        if (selectedItem == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a row to delete.");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete this item?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    MongoDatabase db = Database.getConnection();
                    MongoCollection<Document> collection = db.getCollection("DataEntry");

                    collection.deleteOne(new Document("itemcode", selectedItem.getItemCode()));

                    tabledelete.getItems().remove(selectedItem);

                    Alert success = new Alert(Alert.AlertType.INFORMATION);
                    success.setTitle("Deleted");
                    success.setHeaderText(null);
                    success.setContentText("Item deleted successfully!");
                    success.showAndWait();

                } catch (Exception e) {
                    e.printStackTrace();
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Error");
                    error.setHeaderText(null);
                    error.setContentText("Error deleting item: " + e.getMessage());
                    error.showAndWait();
                }
            }
        });
    }

    @FXML
    public void removedelted() {
        txtItemCodeD.setText("");
        txtProductNameD.setText("");
        txtBatchNoD.setText("");
        txtBonusD.setText("");
        txtCompanyCodeD.setText("");
        txtRetailD.setText("");
        txtBonusPriceD.setText("");
        txtExpiryDateD.setText("");
        txtSalePriceD.setText("");
        txtGroup.setText("");
        txtCompanyDisD.setText("");
        txtCostPriceD.setText("");
        txtSaleTaxD.setText("");
        txtCompanyNameD.setText("");
        txtPiecesPickD.setText("");
        txtQuantityD.setText("");
        tabledelete.getSelectionModel().clearSelection();
    }

    
    
    
    
    
    
    //=================================================Overall page working star =================================
    
    
     public void loadAllProducts3() {
        try {
            MongoDatabase db = Database.getConnection();
            MongoCollection<Document> collection = db.getCollection("DataEntry");

            ObservableList<Product> productList = FXCollections.observableArrayList();

            for (Document doc : collection.find()) {
                productList.add(new Product(
                        doc.getString("itemCode"), doc.getString("productName"),
                        doc.getString("batchNo"), doc.getString("bonus"),
                        doc.getString("companyName"), doc.getString("retail"),
                        doc.getString("salePrice"), doc.getString("group"),
                        doc.getString("companyDis"), doc.getString("costPrice"),
                        doc.getString("saleTax"), doc.getString("companyName2"),
                        doc.getString("piecesPick"), doc.getString("tradePrice"),
                        doc.getString("discount"), doc.getString("bonusPrice")
                ));
            }

            Finalviewtable.setItems(productList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Setup TableView columns
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colBatchNo.setCellValueFactory(new PropertyValueFactory<>("batchNo"));
        colSalePrice.setCellValueFactory(new PropertyValueFactory<>("salePrice"));
        colSaleTax.setCellValueFactory(new PropertyValueFactory<>("saleTax"));
        colCompanyDis.setCellValueFactory(new PropertyValueFactory<>("companyDis"));
        colTradePrice.setCellValueFactory(new PropertyValueFactory<>("tradePrice"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colCostPrice.setCellValueFactory(new PropertyValueFactory<>("costPrice"));
        colCompanyName.setCellValueFactory(new PropertyValueFactory<>("companyName"));

        loadAllProducts();
tableProducts.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Row selection listener to populate TextFields
        tableProducts.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txtItemCodeT.setText(newSelection.getItemCode());
                txtProductNameT.setText(newSelection.getProductName());
                txtBatchNoT.setText(newSelection.getBatchNo());
                txtBonusT.setText(newSelection.getBonus());
                txtCompanyCodeT.setText(newSelection.getCompanyName());
                txtRetailT.setText(newSelection.getRetail());
                txtSalePriceT.setText(newSelection.getSalePrice());
                txtGroupT.setText(newSelection.getGroup());
                txtCompanyDisT.setText(newSelection.getCompanyDis());
                txtCostPriceT.setText(newSelection.getCostPrice());
                txtSaleTaxT.setText(newSelection.getSaleTax());
                txtCompanyNameT.setText(newSelection.getCompanyName2());
                txtPiecesPickT.setText(newSelection.getPiecesPick());
                txtTradePriceT.setText(newSelection.getTradePrice());
                txtDiscountT.setText(newSelection.getDiscount());
                txtBonusPriceT.setText(newSelection.getBonusPrice());
            }
        });
        
        
        
        //==========================================Deleting working ================================
        
         colItemCodeD.setCellValueFactory(new PropertyValueFactory<>("itemCode"));    
    colProductNameD.setCellValueFactory(new PropertyValueFactory<>("productName"));    
    colBatchNoD.setCellValueFactory(new PropertyValueFactory<>("batchNo"));    
    colSalePriceD.setCellValueFactory(new PropertyValueFactory<>("salePrice"));    
    colCompanyDisD.setCellValueFactory(new PropertyValueFactory<>("companyDis"));    
    colTradePriceD.setCellValueFactory(new PropertyValueFactory<>("tradePrice"));    
    colDiscountD.setCellValueFactory(new PropertyValueFactory<>("discount"));    
    colSaleTaxD.setCellValueFactory(new PropertyValueFactory<>("saleTax"));    
    colCompanyNameD.setCellValueFactory(new PropertyValueFactory<>("companyName"));    
    tabledelete.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    // Load data
    loadAllProducts2();        
    tabledelete.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
    if (newSelection != null) {
                txtItemCodeD.setText(newSelection.getItemCode());
                txtProductNameD.setText(newSelection.getProductName());
                txtBatchNoD.setText(newSelection.getBatchNo());
                txtBonusD.setText(newSelection.getBonus());
                txtCompanyCodeD.setText(newSelection.getCompanyName());
                txtRetailD.setText(newSelection.getRetail());
                txtBonusPriceD.setText(newSelection.getSalePrice());
                txtExpiryDateD.setText(newSelection.getGroup());
                txtSalePriceD.setText(newSelection.getTradePrice());
                txtGroupD.setText(newSelection.getDiscount());
                txtCompanyDisD.setText(newSelection.getBonusPrice());
                txtCostPriceD.setText(newSelection.getCostPrice());
                txtSaleTaxD.setText(newSelection.getSaleTax());
                txtCompanyNameD.setText(newSelection.getCompanyName2());
                txtPiecesPickD.setText(newSelection.getPiecesPick());
                txtQuantityD.setText(newSelection.getCompanyDis());

            }
        });

        
    
    
   //===================================================final working start ==============================
   

        
          itemcodeL.setCellValueFactory(new PropertyValueFactory<>("itemCode"));    
    itemnamel.setCellValueFactory(new PropertyValueFactory<>("productName"));    
    batchnol.setCellValueFactory(new PropertyValueFactory<>("batchNo"));    
    salepricel.setCellValueFactory(new PropertyValueFactory<>("salePrice"));    
    companydisl.setCellValueFactory(new PropertyValueFactory<>("companyDis"));    
    tradepricel.setCellValueFactory(new PropertyValueFactory<>("tradePrice"));    
    discountl.setCellValueFactory(new PropertyValueFactory<>("discount"));    
    saletaxl.setCellValueFactory(new PropertyValueFactory<>("saleTax"));    
    companynamel.setCellValueFactory(new PropertyValueFactory<>("companyName"));    
    Finalviewtable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    loadAllProducts3();
    
    
    
  //========================================update working==============================================//
  updatebutton.setOnAction(e -> updateData());
        sidebar.setTranslateX(0);
        startClock();
        setupTreeView();
    }
    
    // ===============================
    // Sidebar toggle
    @FXML
    private void toggleSidebar() {
        double durationMillis = 200;
        Duration duration = Duration.millis(durationMillis);

        boolean sidebarOpen = sidebar.getTranslateX() == 0;
        double moveX = sidebarOpen ? -sidebar.getWidth() + 50 : 0;

        // Animate all panes
        TranslateTransition sidebarSlide = createSmoothSlide(sidebar, moveX, duration);
        TranslateTransition dashSlide = createSmoothSlide(Tree_Dash, moveX, duration);
        TranslateTransition addPanesSlide = createSmoothSlide(add_panes, moveX, duration);
        TranslateTransition addPaneSlide = createSmoothSlide(add_pane, moveX, duration);
        TranslateTransition updatePaneSlide = createSmoothSlide(update_pane, moveX, duration);
        TranslateTransition deletePaneSlide = createSmoothSlide(delete_pane, moveX, duration);
        TranslateTransition viewPaneSlide = createSmoothSlide(view_product, moveX, duration);
        TranslateTransition historyPaneSlide = createSmoothSlide(history_pane, moveX, duration);
        TranslateTransition dayPaneSlide = createSmoothSlide(day_pane, moveX, duration);
        TranslateTransition weekPaneSlide = createSmoothSlide(week_pane, moveX, duration);
        TranslateTransition monthPaneSlide = createSmoothSlide(monthly_pane, moveX, duration);

        sidebarSlide.play(); dashSlide.play(); addPanesSlide.play(); addPaneSlide.play();
        updatePaneSlide.play(); deletePaneSlide.play(); viewPaneSlide.play(); historyPaneSlide.play();
        dayPaneSlide.play(); weekPaneSlide.play(); monthPaneSlide.play();
    }

    private TranslateTransition createSmoothSlide(javafx.scene.Node node, double toX, Duration duration) {
        TranslateTransition slide = new TranslateTransition(duration, node);
        slide.setToX(toX);
        slide.setInterpolator(Interpolator.EASE_BOTH);
        return slide;
    }

    private void startClock() {
        Timeline clock = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            LocalTime time = LocalTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
            dashboardClock.setText(time.format(formatter));
        }));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    private void setupTreeView() {
        TreeItem<String> rootItem = new TreeItem<>("Root");
        rootItem.setExpanded(true);

        TreeItem<String> dashboard = new TreeItem<>("Dashboard");
        TreeItem<String> addProductMain = new TreeItem<>("Add Products");
        TreeItem<String> addProduct = new TreeItem<>("Add Product");
        TreeItem<String> updateProduct = new TreeItem<>("Update Product");
        TreeItem<String> deleteProduct = new TreeItem<>("Delete Product");
        TreeItem<String> viewProduct = new TreeItem<>("View Product");

        TreeItem<String> historyMain = new TreeItem<>("View History");
        TreeItem<String> dayHistory = new TreeItem<>("Day History");
        TreeItem<String> weekHistory = new TreeItem<>("Week History");
        TreeItem<String> monthlyHistory = new TreeItem<>("Monthly History");

        addProductMain.getChildren().addAll(addProduct, updateProduct, deleteProduct, viewProduct);
        rootItem.getChildren().addAll(dashboard, addProductMain, historyMain);
        historyMain.getChildren().addAll(dayHistory, weekHistory, monthlyHistory);

        Tree_View.setRoot(rootItem);
        Tree_View.setShowRoot(false);

        Tree_Dash.setVisible(true); add_panes.setVisible(false); add_pane.setVisible(false);
        update_pane.setVisible(false); delete_pane.setVisible(false); view_product.setVisible(false);
        history_pane.setVisible(false); day_pane.setVisible(false); week_pane.setVisible(false);
        monthly_pane.setVisible(false);

       Tree_View.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
    if (newVal != null) {
        String selected = newVal.getValue();

        if (selected.equals("Dashboard")) {
            showPane(Tree_Dash);
        } else if (selected.equals("Add Products")) {
            showPane(add_panes);
        } else if (selected.equals("Add Product")) {
            showPane(add_pane);
        } else if (selected.equals("Update Product")) {
            showPane(update_pane);
        } else if (selected.equals("Delete Product")) {
            showPane(delete_pane);
        } else if (selected.equals("View Product")) {
            showPane(view_product);
        } else if (selected.equals("View History")) {
            showPane(history_pane);
        } else if (selected.equals("Day History")) {
            showPane(day_pane);
        } else if (selected.equals("Week History")) {
            showPane(week_pane);
        } else if (selected.equals("Monthly History")) {
            showPane(monthly_pane);
        }
    }
});

    }

    private void showPane(AnchorPane targetPane) {
        Tree_Dash.setVisible(false); add_panes.setVisible(false); add_pane.setVisible(false);
        update_pane.setVisible(false); delete_pane.setVisible(false); view_product.setVisible(false);
        history_pane.setVisible(false); day_pane.setVisible(false); week_pane.setVisible(false);
        monthly_pane.setVisible(false);
        targetPane.setVisible(true);
    }
}
