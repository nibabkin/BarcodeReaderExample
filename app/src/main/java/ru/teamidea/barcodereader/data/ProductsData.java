package ru.teamidea.barcodereader.data;

import java.util.ArrayList;

import ru.teamidea.barcodereader.R;

/**
 * Created by nikita on 2/17/17.
 */
public class ProductsData {
    private static ProductsData instance = new ProductsData();

    public static ProductsData getInstance() {
        return instance;
    }

    private ArrayList<Product> productList;

    private ProductsData() {
        productList = new ArrayList<>();

        productList.add(new Product(1, "4607037435513", "Драже Изюм в Какао 150г.", "Продукты", 235.50f, 10, "ООО Гудс Матрикс"));
        productList.add(new Product(2, "4606301000983", "Котл. Картоф. с грибами \"Государь\" 450г.", "Продукты", 276.00f, 20, "ООО \"Госудать\""));
        productList.add(new Product(3, "4810093012332", "Творог 4 пр.жир.Молочный гостинец 240г. РБ", "Продукты", 56.20f, 21, "ООО \"МАЛОЧНЫ ГАСЦИНЕЦ\""));
        productList.add(new Product(4, "4601674034894", "Кукуруза Хайнц 340г. Ж/Б", "Продукты", 45.00f, 5, "ООО \"HEINZ\""));
        productList.add(new Product(5, "8715151030760", "Ром \"Эль Дорадо\" темный, 0,7 л", "Продукты", 1990.90f, 43, "ООО \"Алеанта Групп\""));

        productList.add(new Product(6, "4606982004973", "Жидкость двухкомпонентная от комаров", "Бытовая Химия", 39.00f, 12, "NEON OXI SPRAY"));
        productList.add(new Product(7, "4607131424826", "Саше от моли с запахом жасмина (Раптор), ЗАО Инвест Россия", "Бытовая Химия", 109.90f, 1, "MOSQUITALL"));
        productList.add(new Product(8, "9339238000126", "Пятновыводит. NEON OXI SPRAY 300мл", "Бытовая Химия", 131.00f, 101, "АПТОР"));
        productList.add(new Product(9, "4015400991977", "FAIRY д/посуды 0,6л", "Бытовая Химия", 149.90f, 3, "\"FAIRY\""));
        productList.add(new Product(10, "5410076202898", "АМБИ ПЮР осв. возд. 300 мл", "Бытовая Химия", 320.00f, 98, "AMBI PUR"));

        productList.add(new Product(11, "5009612110102", "ПРЕЗЕРВАТИВЫ SENSE УЛЬТРАТОНКИЕ  12 ШТ.", "Медицинские товары", 776.80f, 1025, "GANZO (ГАНЗО)"));
        productList.add(new Product(12, "3574660709407", "Компид пластырь от врастающих, мозолей между пальцами ног №10", "Медицинские товары", 12.50f, 89, "ООО Берлин-Фарма Калуга"));
        productList.add(new Product(13, "4823015900044", "Ф/ч Тайфун Лотос №30 пакетики", "Медицинские товары", 44.10f, 43, "НИЖФАРМ"));
        productList.add(new Product(14, "4607064971961", "сибир/зд.бальзам корень", "Медицинские товары", 249.90f, 65, "МСД фармасьютикалс"));
        productList.add(new Product(15, "4605113000464", "Горца птичьего (спорыша) трава 50г", "Медицинские товары", 29.90f, 3, "НИЖФАРМ"));

        productList.add(new Product(16, "4620768021404", "ВИЛКИ БИОРАЗЛАГАЕМЫЕ БИО 6ШТ АНТЕЛЛА", "Посуда", 32.20f, 8, "ООО Башкирский Фарфор"));
        productList.add(new Product(17, "4607127001864", "Бокалы HS для шампанского 6шт", "Посуда", 621.90f, 7, "ООО Эмаль"));
        productList.add(new Product(18, "4250195112941", "GIPFEL Кастрюля SAUCEPOT 5,4 л", "Посуда", 1129.00f, 12, "ПЛАСТ УПАКОВКА"));
        productList.add(new Product(19, "4823045800222", "арт.А263 Сков-да Д260 с пл.руч. и кр.(ровн.дно)", "Посуда", 1399.20f, 11, "ООО Спектр Прибор"));
        productList.add(new Product(20, "4607129347809", "Скороварка Ока СВ-7010", "Посуда", 789.90f, 2, "ООО Спектр Прибор"));
    }

    public Product getProductByCode(String code) {
        for (Product product : productList) {
            if (product.getCode().equalsIgnoreCase(code)) {
                return product;
            }
        }
        return null;
    }

    public ArrayList<Product> getProductsStartsWith(String startCode) {
        ArrayList<Product> filteredProducts = new ArrayList<>();
        for (Product product : productList) {
            if (product.getCode().startsWith(startCode)) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }

    public Product getProductById(int id) {
        for (Product product : productList) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public int getImageResourceByProductId(int id) {
        switch (id) {
            case 1:
                return R.drawable.ic_1;
            case 2:
                return R.drawable.ic_2;
            case 3:
                return R.drawable.ic_3;
            case 4:
                return R.drawable.ic_4;
            case 5:
                return R.drawable.ic_5;
            case 6:
                return R.drawable.ic_6;
            case 7:
                return R.drawable.ic_7;
            case 8:
                return R.drawable.ic_8;
            case 9:
                return R.drawable.ic_9;
            case 10:
                return R.drawable.ic_10;
            case 11:
                return R.drawable.ic_11;
            case 12:
                return R.drawable.ic_12;
            case 13:
                return R.drawable.ic_13;
            case 14:
                return R.drawable.ic_14;
            case 15:
                return R.drawable.ic_15;
            case 16:
                return R.drawable.ic_16;
            case 17:
                return R.drawable.ic_17;
            case 18:
                return R.drawable.ic_18;
            case 19:
                return R.drawable.ic_19;
            case 20:
                return R.drawable.ic_20;
        }
        return 0;
    }
}
