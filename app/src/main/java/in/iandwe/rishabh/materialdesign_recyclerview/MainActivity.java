package in.iandwe.rishabh.materialdesign_recyclerview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Movie> movie;
    RecyclerView recyclerView;
    MoviesAdapter mAdapter;

    //1. Initialise the Recycler View
    //2. Set the Layout Manager that manages all the view
    // 3/ set oriantatiom
    //4. Recyler view hasFixed size
    //5. initialise the ArrayList
    //6. populate the method to add data to the Array List
    //7. Create the addToDataMovies() funstion by Movie Class
    //8. Create the objevt of Movie Adapter and pass the array List inside the Adapter as parameter
    //9. Add the constructor to the ArrayAdapter
    //10  Add the 3 method.One to detect the rows. Two to create the view and three to Bind the view
    // 11. OnBindViewHolder populates the data to the various views of the holder
    //12. This helps us recycle view instead of creating new everytime and hence better than ListView

/********************************************************************************/

    public interface RecyclerViewItemListener
    {
        public void onClick(View v,int position);
        public void onLongClick(View v,int position);
    }

    public class MyCustomClickListener implements RecyclerView.OnItemTouchListener {

        RecyclerViewItemListener listen;
        GestureDetector gesture;
        public MyCustomClickListener(Context context, final RecyclerView r,
                                     RecyclerViewItemListener li)
        {
            listen=li;
            gesture=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View v=r.findChildViewUnder(e.getX(),e.getY());
                    if(v!=null && listen!=null)
                        listen.onLongClick(v,r.getChildLayoutPosition(v));
                }
            });

        }
        //touch Listener
        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View v=rv.findChildViewUnder(e.getX(),e.getY());
            if(v!=null && listen!=null&& gesture.onTouchEvent(e))
                listen.onClick(v,rv.getChildLayoutPosition(v));
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {


        }



        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
/****************************************************************************8*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        //Assigning a layout manager to set the recycler view
        LinearLayoutManager manager=new LinearLayoutManager(this);
        manager.setOrientation(LinearLayout.VERTICAL);
        recyclerView.setLayoutManager(manager);

        /*****************************************************************************/
        //adding the on item click listener to the recycler view
        recyclerView.addOnItemTouchListener(new MyCustomClickListener(this, recyclerView, new RecyclerViewItemListener() {
            @Override
            public void onClick(View v, int position) {
                mAdapter.deleteMovieAt(position);
            }

            @Override
            public void onLongClick(View v, int position) {
                mAdapter.deleteMovieAt(position);
            }
        }));
        //adding defalt animator
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);

       /***************************************************************************************************************/

        recyclerView.setHasFixedSize(true);
        movie=new ArrayList<>();


        //Creating the Objevt of Movies Adapter and passing the list view inside the Adapter
        mAdapter=new MoviesAdapter(movie);

        //Attach a recycler view to tha adapter
        recyclerView.setAdapter(mAdapter);
        //Add this after setting the Adapter to the Recycler View

        addDataToMovies();
    }

    private void addDataToMovies() {
        //Calling the Encapsulated Function
        Movie movie1=new Movie("Race3","Cant Understand",2018);
        //adding to the ArrayList<>
        movie.add(movie1);

        movie1=new Movie("Bahubali","UnBelievable",2018);
        //adding to the ArrayList<>
        movie.add(movie1);

        movie1=new Movie("Raaz","Horror",2018);
        //adding to the ArrayList<>
        movie.add(movie1);

        movie1=new Movie("Raaz3","Comedy",2018);
        //adding to the ArrayList<>
        movie.add(movie1);

        movie1=new Movie("Krrish","Science Friction",2018);
        //adding to the ArrayList<>
        movie.add(movie1);

        movie1=new Movie("Krrish 3","No Science No Friction",2018);
        //adding to the ArrayList<>
        movie.add(movie1);
        movie1=new Movie("Krrish 3","No Science No Friction",2018);
        //adding to the ArrayList<>
        movie.add(movie1);

        movie1=new Movie("Krrish 3","No Science No Friction",2018);
        //adding to the ArrayList<>
        movie.add(movie1);movie1=new Movie("Krrish 3","No Science No Friction",2018);
        //adding to the ArrayList<>
        movie.add(movie1);
        movie1=new Movie("Krrish 3","No Science No Friction",2018);
        //adding to the ArrayList<>
        movie.add(movie1);
        movie1=new Movie("Krrish 3","No Science No Friction",2018);
        //adding to the ArrayList<>
        movie.add(movie1);movie1=new Movie("Krrish 3","No Science No Friction",2018);
        //adding to the ArrayList<>
        movie.add(movie1);movie1=new Movie("Krrish 3","No Science No Friction",2018);
        //adding to the ArrayList<>
        movie.add(movie1);movie1=new Movie("Krrish 3","No Science No Friction",2018);
        //adding to the ArrayList<>
        movie.add(movie1);

        //Notify when the data has been changed and the data will be changes by the adapter accordingly
        mAdapter.notifyDataSetChanged();
    }


    //for holding the ArrayList we yse Recycler View.ViewHolder
    public class MoviesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        ArrayList<Movie> moviesData;
        public MoviesAdapter(ArrayList<Movie> moviesData) {
            this.moviesData=moviesData;
        }

        //This method will create the view
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
          //  View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_layout,parent,false);
            //layout connection with card layout
            if(viewType==ViewTypes.VIEW_TYPES){
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header, parent, false);
                return new MyHeaderHolder(v);
            }else {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_card, parent, false);
                return new MyMovieHolderRow(v);
            }
        }

        // This method will bind the view
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if(position==0){

            }else {
                //getting the data corresponding to the position
                Movie m = moviesData.get(position-1);
                //Cast this to the main type
                MyMovieHolderRow movieRow = (MyMovieHolderRow) holder;
                //adding the title,genre and year
                movieRow.title.setText(m.getTitle());
                movieRow.genre.setText(m.getGenre());
                movieRow.year.setText(String.valueOf(m.getYear()));
            }
        }

        /****************************************************************************/
        public void deleteMovieAt(int pos){

            moviesData.remove(pos);
            notifyItemRemoved(pos);
        }
        /***************************************************************/
        @Override
        public int getItemCount() {
            return moviesData.size()+1;
        }

        public int getItemViewType(int position){
            //return super.getItemViewType(position);

            if(position==0){
                return ViewTypes.VIEW_TYPES;
            }else{
                return ViewTypes.VIEW_ROW;
            }

        }



        public class MyMovieHolderRow extends RecyclerView.ViewHolder{
            public TextView title,genre,year;

            public MyMovieHolderRow(View itemView) {
                super(itemView);
                title=(TextView)itemView.findViewById(R.id.title);
                genre=(TextView)itemView.findViewById(R.id.genre);
                year=(TextView)itemView.findViewById(R.id.year);


            }
        }

        //Creating Holder for header
        public class MyHeaderHolder extends RecyclerView.ViewHolder{
            TextView txtHeader;
            public MyHeaderHolder(View itemView) {
                super(itemView);
                txtHeader=(TextView)itemView.findViewById(R.id.headerText);

            }
        }
    }
    class ViewTypes{
        public static final int VIEW_TYPES=1;
        public static final int VIEW_ROW=2;
    }


}
