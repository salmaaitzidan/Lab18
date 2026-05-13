package com.example.viewmodellivedatademoenrichi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CounterViewModel extends ViewModel {

        // MutableLiveData = modifiable depuis le ViewModel
        private final MutableLiveData<Integer> countLiveData = new MutableLiveData<>();

        public CounterViewModel() {
            countLiveData.setValue(0);  // Valeur initiale (appelée une seule fois)
        }

        public void increment() {
            Integer current = countLiveData.getValue();
            if (current != null) {
                countLiveData.setValue(current + 1);  // met à jour tous les observers
            }
        }

        public void decrement() {
            Integer current = countLiveData.getValue();
            if (current != null) {
                countLiveData.setValue(current - 1);
            }
        }

        public void reset() {
            countLiveData.setValue(0);
        }

        // Getter exposé à l'Activity (lecture seule = bonne pratique)
        public LiveData<Integer> getCount() {
            return countLiveData;
        }
    }

