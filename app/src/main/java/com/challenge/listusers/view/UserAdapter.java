package com.challenge.listusers.view;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.challenge.listusers.R;
import com.challenge.listusers.databinding.ItemListUsersBinding;
import com.challenge.listusers.model.User;

import java.util.List;

public class UserAdapter extends ListAdapter<User, UserAdapter.UserViewHolder> {


    protected UserAdapter() {
        super(new DiffUtil.ItemCallback<User>() {
            @Override
            public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
                return oldItem.equals(newItem);
            }
        });
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemListUsersBinding binding = ItemListUsersBinding.inflate(inflater, parent, false);
        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User currentUser = getItem(position);
        holder.bind(currentUser);
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        private final ItemListUsersBinding binding;

        private final ImageView image;
        private final TextView name;
        private final TextView address;
        private final TextView cpfCnpj;

        public UserViewHolder(@NonNull ItemListUsersBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            name = binding.nameTxt;
            image = binding.profileImg;
            address = binding.addressTxt;
            cpfCnpj = binding.cpfCnpjTxt;

            binding.editImgBtn.setOnClickListener(v -> {
                // Lógica para editar o usuário
            });

            binding.deleteImgBtn.setOnClickListener(v -> {
                // Lógica para excluir o usuário
            });
        }

        public void bind(User user) {
            name.setText(user.getName());
            address.setText(user.getAddress());
            cpfCnpj.setText(user.getCpfCnpj());
            Glide.with(itemView.getContext())
                    .load(user.getImage())
                    .placeholder(R.drawable.camera)
                    .error(R.drawable.camera)
                    .into(image);
        }
    }
}
