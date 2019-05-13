package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void updateInStorage(Resume resume, int index) {
        storage[index] = resume;
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    public void saveToStorage(Resume resume, int index) {
        if(size == STORAGE_LIMIT)
            throw new StorageException("Storage overflow!", resume.getUuid());
        saveElement(resume, index);
        size++;
    }

    @Override
    public void deleteFromStorage(int index) {
        deleteElement(index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected Resume getFromStorage(int index) {
        return storage[index];
    }

    protected abstract void saveElement(Resume resume, int index);

    protected abstract void deleteElement(int index);

    protected abstract int getIndex(String uuid);
}