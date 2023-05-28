package com.example.assignment5;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private ListView listViewBooks;
    private EditText editTextSearch;
    private Button buttonSearch;
    private List<Book> bookList;
    private List<Book> bookListt;
    private ArrayAdapter<Book> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        listViewBooks = findViewById(R.id.listViewBooks);
        editTextSearch = findViewById(R.id.editTextSearch);
        buttonSearch = findViewById(R.id.buttonSearch);

        // Create a static list of books with titles and prices
        bookListt = new ArrayList<>();
        bookListt.add(new Book("The Catcher in the Rye", "$10.99"));
        bookListt.add(new Book("The Lord of the Rings", "$12.99"));
        bookListt.add(new Book("Harry Potter and the Philosopher's Stone", "$8.99"));
        bookListt.add(new Book("The Great Gatsby", "$14.99"));
        bookListt.add(new Book("Moby-Dick", "$9.99"));
        bookListt.add(new Book("1984", "$11.99"));
        bookListt.add(new Book("To Kill a Mockingbird", "$7.99"));
        bookListt.add(new Book("Pride and Prejudice", "$13.99"));
        bookList = new ArrayList<>();
        bookList.add(new Book("The Catcher in the Rye", "$10.99"));
        bookList.add(new Book("The Lord of the Rings", "$12.99"));
        bookList.add(new Book("Harry Potter and the Philosopher's Stone", "$8.99"));
        bookList.add(new Book("The Great Gatsby", "$14.99"));
        bookList.add(new Book("Moby-Dick", "$9.99"));
        bookList.add(new Book("1984", "$11.99"));
        bookList.add(new Book("To Kill a Mockingbird", "$7.99"));
        bookList.add(new Book("Pride and Prejudice", "$13.99"));

        // Create an ArrayAdapter to populate the ListView with book titles and prices
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bookList);
        listViewBooks.setAdapter(adapter);

        // Set click listener for the search button to filter the book list
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = editTextSearch.getText().toString();
                filterBooks(searchQuery);
            }
        });
        listViewBooks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected book
                Book selectedBook = adapter.getItem(position);

                // Create an intent to launch the PaymentActivity
                Intent intent = new Intent(HomeActivity.this, PaymentActivity.class);
                intent.putExtra("title", selectedBook.getTitle());
                intent.putExtra("price", selectedBook.getPrice());
                startActivity(intent);
            }
        });

    }

    private void filterBooks(String searchQuery) {
        List<Book> filteredList = new ArrayList<>();

        if (searchQuery.isEmpty()) {
            // If search query is empty, display all books
//            Toast.makeText(getApplicationContext(),"test 1",Toast.LENGTH_LONG).show();
//            listViewBooks.setAdapter(adapter);
            filteredList.addAll(bookListt);
        } else {
            // Filter the book list based on the search query
            for (Book book : bookList) {
                if (book.getTitle().toLowerCase().contains(searchQuery.toLowerCase())) {
                    filteredList.add(book);
                }
            }
        }

        if (filteredList.isEmpty()) {
            // If no matching books found, display all books
//            Toast.makeText(getApplicationContext(),"test",Toast.LENGTH_LONG).show();
            filteredList.addAll(bookListt);
        }

        // Update the adapter with the filtered book list
        adapter.clear();
        adapter.addAll(filteredList);
        adapter.notifyDataSetChanged();
    }






    private static class Book {
        private String title;
        private String price;

        public Book(String title, String price) {
            this.title = title;
            this.price = price;
        }

        public String getTitle() {
            return title;
        }

        public String getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return title + " - " + price;
        }
    }
}
