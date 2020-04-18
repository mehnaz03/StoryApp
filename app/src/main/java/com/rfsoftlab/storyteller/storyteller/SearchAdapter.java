package com.rfsoftlab.storyteller.storyteller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private Context context;
    private List<AnimalNames>data;
    private ArrayList<AnimalNames> arraylist;
    private ItemClickListener mClickListener;

    public SearchAdapter(Context activity,  List<AnimalNames>data) {
        this.context = activity;
        this.data = data ;
        this.arraylist = new ArrayList<AnimalNames>();
        this.arraylist.addAll(data);

    }


    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_items, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {


        viewHolder.textView.setText(data.get(position).getAnimalName());
//        viewHolder.textView1.setText(data.get(position).getID());
//        viewHolder.mImageButton.setOnClickListener ( new View.OnClickListener () {
//
//            @Override
//            public void onClick(View v) {
////                showPopupMenu(v);
////                collectorId = items.get(position).getCollectorID();
//            }
//        } );

    }

    @Override
    public int getItemCount() {
        return data .size();
    }




    /**
     * View holder to display each RecylerView item
     */
    protected class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        @BindView(R.id.image)
//        CircleImageView imageView1;
        @BindView(R.id.text)
        TextView textView;
//        @BindView ( R.id.imbPopUp )
//        ImageButton mImageButton;
//        @BindView(R.id.text1)
//        TextView textView1;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }


    }

    //
// allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

//    //    /**
////     * Showing popup menu when tapping on 3 dots
////     */
//    private void showPopupMenu(View view) {
//        // inflate menu
//        PopupMenu popup = new PopupMenu(context, view);
//        MenuInflater inflater = popup.getMenuInflater();
//        inflater.inflate(R.menu.dropdown_menu, popup.getMenu());
//        popup.setOnMenuItemClickListener(new BankAdapter.MyMenuItemClickListener());
//        popup.show();
//    }
//
//    /**
//     * Click listener for popup menu items
//     */
//    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {
//        private int position;
//
//
//        public MyMenuItemClickListener() {
//
//        }
//
//        @Override
//        public boolean onMenuItemClick(MenuItem menuItem) {
//            switch (menuItem.getItemId()) {
//                case R.id.edit:
//                    Intent nextScreen = new Intent(context.getApplicationContext(), ShowCollectorActivity.class);
//                    nextScreen.putExtra("collectorId", collectorId);
//                    context.startActivity(nextScreen);
//
//                    return true;
//
//
//                case R.id.delete:
//                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
//
//                    // Setting Dialog Title
//                    builder.setTitle("Delete");
//
//                    // Setting Dialog Message
//                    builder.setMessage("Do you want to delete this?");
//
//// Setting Icon to Dialog
//                    builder.setIcon(R.drawable.delete);
//                    // Setting Positive "Yes" Button
//                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
////                            Intent toDeleteActivity = new Intent(context.getApplicationContext(), DeleteDocumentActivity.class);
////                            toDeleteActivity.putExtra("documentId",documentId);
////                            toDeleteActivity.putExtra("theFolderId",theFolderId);
////                            Log.d("DLKJAL: ", "FolderOld id " + theFolderId);
//
////
////                            try {
////
////
////                                ApiService api = ApiClient.getClient().create(ApiService.class);
////                                Call<CollectorItem> collectorCall = api.deleteTheCollector(collectorId);
////
////                               collectorCall.enqueue(new Callback<CollectorItem>() {
////                                    @Override
////                                    public void onResponse(Call<CollectorItem> call, Task<CollectorItem> response) {
////
////
//////                                        showToast(response.body().getMessage());
////
////                                        customView.refreshView();
////                                    }
////
////                                    @Override
////                                    public void onFailure(Call<CollectorItem> call, Throwable t) {
////
//////                                        showToast("Error! " + t.getMessage());
////                                    }
////                                });
////                            } catch (Exception e) {
////
////                            }
//                            //notifyDataSetChanged();
//
//
//                        }
//                    });
//
//                    // Setting Negative "NO" Button
//                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            // User pressed No button. Write Logic Here
//                            Toast.makeText(context, "I don't want to delete this.", Toast.LENGTH_LONG).show();
//                        }
//                    });
//
//                    builder.show();
//
//                default:
//                    break;
//            }
//
//
//            return true;
//        }
//
//    }

    //    public void filterList(List<BankItem> filterdNames) {
//        this.data = filterdNames;
//        notifyDataSetChanged();
//    }
// Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        data.clear();
        if (charText.length() == 0) {
            data.addAll(arraylist);
        } else {
            for (AnimalNames wp : arraylist) {
                if (wp.getAnimalName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    data.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}
